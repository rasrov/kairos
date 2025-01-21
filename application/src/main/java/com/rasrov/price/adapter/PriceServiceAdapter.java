package com.rasrov.price.adapter;

import com.rasrov.price.api.PriceService;
import com.rasrov.price.domain.EffectiveDates;
import com.rasrov.price.domain.ErrorResponse;
import com.rasrov.price.domain.PriceResponse;
import com.rasrov.price.entity.PriceEntity;
import com.rasrov.price.serviceport.PriceServicePort;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;

@Service
public class PriceServiceAdapter implements PriceService {

    private final PriceServicePort priceServicePort;

    public PriceServiceAdapter(PriceServicePort priceServicePort) {
        this.priceServicePort = priceServicePort;
    }

    @Override
    @Cacheable("prices")
    public PriceResponse findPrice(final Integer brandId, final Integer productId, final LocalDateTime dateTime) {
        final Set<PriceEntity> prices = priceServicePort.findPrices(brandId, productId, dateTime);

        return prices.stream()
                .max(Comparator.comparingInt(PriceEntity::getPriority))
                .map(this::buildPrice)
                .orElse(buildPriceResponseErrorMessage(brandId, productId, dateTime));
    }

    private PriceResponse buildPrice(final PriceEntity priceEntity) {
        final Double pvpPrice = calculatePriceApplyingFee(priceEntity.getPriceList().getFee(), priceEntity.getPrice());
        final EffectiveDates effectiveDates = new EffectiveDates(priceEntity.getStartDate(), priceEntity.getEndDate());

        return new PriceResponse(priceEntity.getProduct().getId(), priceEntity.getBrand().getId(), priceEntity.getPriceList().getFee(), effectiveDates, pvpPrice, null);
    }

    private PriceResponse buildPriceResponseErrorMessage(final Integer brandId, final Integer productId, final LocalDateTime dateTime) {
        return new PriceResponse(null, null, null, null, null,
                new ErrorResponse(String.format("Not found any active price with brand id %s, product id %s and %s date",
                        brandId, productId, dateTime)));
    }

    private Double calculatePriceApplyingFee(final Integer fee, final Double price) {
        return roundPrice(price + price * (fee / 100.0));
    }

    private Double roundPrice(final Double pvpPrice) {
        return Math.round(pvpPrice * 1000.0) / 1000.0;
    }
}
