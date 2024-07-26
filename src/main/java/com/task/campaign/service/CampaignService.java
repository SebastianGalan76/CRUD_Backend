package com.task.campaign.service;

import com.task.campaign.database.model.Campaign;
import com.task.campaign.database.repository.CampaignRepository;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class CampaignService {
    final BigDecimal MIN_BID_AMOUNT = new BigDecimal("0.2");
    final CampaignRepository campaignRepository;

    public ResponseEntity<String> createCampaign(Campaign campaign) {
        ResponseEntity<String> verificationResult = verifyCampaignData(campaign);
        if (verificationResult.getStatusCode() != HttpStatus.OK) {
            return verificationResult;
        }

        campaignRepository.save(campaign);
        return ResponseEntity.ok().build();
    }
    public ResponseEntity<String> editCampaign(Long id, Campaign campaign) {
        ResponseEntity<String> verificationResult = verifyCampaignData(campaign);
        if (verificationResult.getStatusCode() != HttpStatus.OK) {
            return verificationResult;
        }

        Campaign savedCampaign = findById(id);
        if (savedCampaign == null) {
            return null;
        }

        savedCampaign.setName(campaign.getName());
        savedCampaign.setBidAmount(campaign.getBidAmount());
        savedCampaign.setCampaignFund(campaign.getCampaignFund());
        savedCampaign.setStatus(campaign.isStatus());
        savedCampaign.setKeywordList(campaign.getKeywordList());
        savedCampaign.setCity(campaign.getCity());
        savedCampaign.setRadius(campaign.getRadius());
        campaignRepository.save(savedCampaign);
        return ResponseEntity.ok().build();
    }

    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }
    public @Nullable Campaign findById(Long id) {
        return campaignRepository.findById(id).orElse(null);
    }
    public void deleteById(Long id) {
        campaignRepository.deleteById(id);
    }

    private ResponseEntity<String> verifyCampaignData(Campaign campaign) {
        if (campaign.getBidAmount().compareTo(MIN_BID_AMOUNT) < 0) {
            return ResponseEntity.badRequest().body("The bid amount should be greater than or equal to " + MIN_BID_AMOUNT);
        }

        if (campaign.getBidAmount().compareTo(campaign.getCampaignFund()) < 0) {
            return ResponseEntity.badRequest().body("The bid amount cannot be less than the campaign fund");
        }

        if (campaign.getRadius() < 0) {
            return ResponseEntity.badRequest().body("The radius cannot be less than 0");
        }

        return ResponseEntity.ok().build();
    }
}
