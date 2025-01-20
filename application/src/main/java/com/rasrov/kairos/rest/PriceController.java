package com.rasrov.kairos.rest;

import com.rasrov.kairos.api.PriceService;
import com.rasrov.kairos.domain.PriceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/price")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> findPrice(
            @RequestParam("brandId") Integer brandId,
            @RequestParam("productId") Integer productId,
            @RequestParam("startDate") LocalDateTime startDate
            ) {
        final PriceResponse priceResponse = priceService.findPrice(
                brandId,
                productId,
                startDate
        );
        return ResponseEntity
                .status(Objects.nonNull(priceResponse.error()) ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                .body(priceResponse);
    }

}
