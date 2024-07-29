package com.task.campaign.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDto {
    String name;

    BigDecimal bidAmount;
    BigDecimal campaignFund;

    boolean status;
    String[] keywords;

    String city;
    int radius;
}
