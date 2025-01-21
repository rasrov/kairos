package com.rasrov.price.adapter;

import com.rasrov.price.entity.PriceEntity;
import com.rasrov.price.repository.PriceRepository;
import com.rasrov.price.serviceport.PriceServicePort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class PriceAdapter implements PriceServicePort {

    private final PriceRepository priceRepository;

    public PriceAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Set<PriceEntity> findPrices(final Integer brandId, final Integer productId, final LocalDateTime dateTime) {
        return priceRepository.findByBrandIdAndProductIdAndDateTimeBetween(
                brandId, productId, dateTime);
    }
}
