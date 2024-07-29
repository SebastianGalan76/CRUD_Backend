package com.task.campaign.controller;

import com.task.campaign.data.CampaignDto;
import com.task.campaign.database.model.Campaign;
import com.task.campaign.service.CampaignService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequiredArgsConstructor
public class CampaignController {
    final CampaignService campaignService;

    @PostMapping("/api/campaign")
    public ResponseEntity<String> createCampaign(@RequestBody CampaignDto campaign) {
        return campaignService.createCampaign(campaign);
    }

    @PutMapping("/api/campaign/{id}")
    public ResponseEntity<String> editCampaign(@PathVariable Long id, @RequestBody CampaignDto campaign) {
        return campaignService.editCampaign(id, campaign);
    }

    @DeleteMapping("/api/campaign/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        campaignService.deleteById(id);
    }

    @GetMapping("/api/campaign/{id}")
    public Campaign getCampaignById(@PathVariable Long id) {
        return campaignService.findById(id);
    }

    @GetMapping("/api/campaign")
    public List<Campaign> getAllCampaigns() {
        return campaignService.findAll();
    }
}
