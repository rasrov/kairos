package com.rasrov.kairos.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
public class PriceEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id")
    private PriceListEntity priceList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "price")
    private Double price;

    @Column(name = "curr")
    private String currency;

    public PriceEntity() {
    }

    public PriceEntity(Integer id, BrandEntity brand, LocalDateTime startDate, LocalDateTime endDate, PriceListEntity priceList, Integer priority, Double price, ProductEntity product, String currency) {
        this.id = id;
        this.brand = brand;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.priority = priority;
        this.price = price;
        this.product = product;
        this.currency = currency;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public PriceListEntity getPriceList() {
        return priceList;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public Double getPrice() {
        return price;
    }
}
