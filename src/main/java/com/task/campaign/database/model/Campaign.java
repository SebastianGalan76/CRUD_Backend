package com.task.campaign.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    BigDecimal bidAmount;
    BigDecimal campaignFund;

    boolean status;

    @Column(length = 2000)
    String keywords;

    @ManyToOne
    @JoinColumn(name = "city_id")
    City city;
    int radius;
}
