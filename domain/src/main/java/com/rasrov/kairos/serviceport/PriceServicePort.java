package com.rasrov.kairos.serviceport;

import com.rasrov.kairos.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.Set;

public interface PriceServicePort {

    Set<PriceEntity> findPrices(Integer brandId, Integer productId, LocalDateTime dateTime);

}
