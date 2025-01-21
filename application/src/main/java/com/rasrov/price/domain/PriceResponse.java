package com.rasrov.price.domain;

public record PriceResponse(Integer productId, Integer brandId, Integer fee, EffectiveDates effectiveDates, Double price, ErrorResponse error) {}
