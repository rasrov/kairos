package com.rasrov.price.api;

import com.rasrov.price.domain.PriceResponse;

import java.time.LocalDateTime;

public interface PriceService {

    PriceResponse findPrice(Integer brandId, Integer productId, LocalDateTime startDate);

}
