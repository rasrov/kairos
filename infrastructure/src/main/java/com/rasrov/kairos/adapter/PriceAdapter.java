package com.rasrov.kairos.adapter;

import com.rasrov.kairos.entity.PriceEntity;
import com.rasrov.kairos.repository.PriceRepository;
import com.rasrov.kairos.serviceport.PriceServicePort;
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
