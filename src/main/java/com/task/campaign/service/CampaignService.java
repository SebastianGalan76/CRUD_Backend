package com.task.campaign.service;

import com.task.campaign.data.CampaignDto;
import com.task.campaign.database.model.Campaign;
import com.task.campaign.database.model.City;
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
    final BigDecimal MIN_BID_AMOUNT = new BigDecimal("0.02");

    final CampaignRepository campaignRepository;
    final CityService cityService;

    public ResponseEntity<String> createCampaign(CampaignDto campaignDto) {
        ResponseEntity<String> verificationResult = verifyCampaignData(campaignDto);
        if (verificationResult.getStatusCode() != HttpStatus.OK) {
            return verificationResult;
        }
        Campaign campaign = new Campaign();

        ResponseEntity<String> conversionResult = convertDtoToModel(campaign, campaignDto);
        if(conversionResult.getStatusCode() != HttpStatus.OK){
            return conversionResult;
        }

        campaignRepository.save(campaign);
        return ResponseEntity.ok().body("You have created new campaign successfully");
    }
    public ResponseEntity<String> editCampaign(Long id, CampaignDto campaignDto) {
        ResponseEntity<String> verificationResult = verifyCampaignData(campaignDto);
        if (verificationResult.getStatusCode() != HttpStatus.OK) {
            return verificationResult;
        }

        Campaign savedCampaign = findById(id);
        if (savedCampaign == null) {
            return ResponseEntity.badRequest().body("There is no campaign with the specified ID");
        }

        ResponseEntity<String> conversionResult = convertDtoToModel(savedCampaign, campaignDto);
        if(conversionResult.getStatusCode() != HttpStatus.OK){
            return conversionResult;
        }

        campaignRepository.save(savedCampaign);
        return ResponseEntity.ok().body("You have updated new campaign successfully");
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

    private ResponseEntity<String> verifyCampaignData(CampaignDto campaignDto) {
        if (campaignDto.getBidAmount().compareTo(MIN_BID_AMOUNT) < 0) {
            return ResponseEntity.badRequest().body("The bid amount should be greater than or equal to " + MIN_BID_AMOUNT);
        }

        if (campaignDto.getCampaignFund().compareTo(campaignDto.getBidAmount()) < 0) {
            return ResponseEntity.badRequest().body("The campaign fund cannot be less than the bid amount");
        }

        if (campaignDto.getRadius() < 0) {
            return ResponseEntity.badRequest().body("The radius cannot be less than 0");
        }

        return ResponseEntity.ok().build();
    }

    private ResponseEntity<String> convertDtoToModel(Campaign campaign, CampaignDto campaignDto){
        campaign.setName(campaign.getName());
        campaign.setBidAmount(campaignDto.getBidAmount());
        campaign.setCampaignFund(campaignDto.getCampaignFund());
        campaign.setStatus(campaignDto.isStatus());

        StringBuilder sb = new StringBuilder();
        for(String keyword:campaignDto.getKeywords()){
            sb.append(keyword).append(";");
        }
        campaign.setKeywords(sb.toString());

        City city = cityService.getCityByName(campaignDto.getCity());
        if(city == null){
            return ResponseEntity.badRequest().body("There is no city with the given name in our system. Select a city from the list");
        }
        campaign.setCity(city);
        campaign.setRadius(campaign.getRadius());
        return ResponseEntity.ok().build();
    }
}
