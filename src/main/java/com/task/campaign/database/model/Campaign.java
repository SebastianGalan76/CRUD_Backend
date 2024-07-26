package com.task.campaign.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "campaign_keywords",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")

    )
    List<Keyword> keywordList;

    @ManyToOne
    @JoinColumn(name = "city_id")
    City city;
    int radius;
}
