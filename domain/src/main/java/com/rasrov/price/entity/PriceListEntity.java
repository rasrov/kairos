package com.rasrov.price.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRICE_LIST")
public class PriceListEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "fee")
    private Integer fee;

    public PriceListEntity() {
    }

    public PriceListEntity(Integer id, Integer fee) {
        this.id = id;
        this.fee = fee;
    }

    public Integer getFee() {
        return fee;
    }
}
