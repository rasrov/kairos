package com.rasrov.kairos.repository;

import com.rasrov.kairos.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Set;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    @Query(value = """
            SELECT *
            FROM PRICES p
            WHERE p.BRAND_ID = :brandId
            AND p.PRODUCT_ID = :productId
            AND :dateTime BETWEEN p.START_DATE AND p.END_DATE
            """, nativeQuery = true)
    Set<PriceEntity> findByBrandIdAndProductIdAndDateTimeBetween(
            @Param("brandId") Integer brandId,
            @Param("productId") Integer productId,
            @Param("dateTime") LocalDateTime dateTime
    );

}
