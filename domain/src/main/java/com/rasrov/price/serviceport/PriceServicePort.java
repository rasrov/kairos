package com.rasrov.price.serviceport;

import com.rasrov.price.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.Set;

public interface PriceServicePort {

    Set<PriceEntity> findPrices(Integer brandId, Integer productId, LocalDateTime dateTime);

}
