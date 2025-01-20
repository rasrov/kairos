package com.rasrov.kairos.api;

import com.rasrov.kairos.domain.PriceResponse;

import java.time.LocalDateTime;

public interface PriceService {

    PriceResponse findPrice(Integer brandId, Integer productId, LocalDateTime startDate);

}
