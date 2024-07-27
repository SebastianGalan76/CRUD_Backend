package com.task.campaign.controller;

import com.task.campaign.data.CampaignDto;
import com.task.campaign.database.model.Campaign;
import com.task.campaign.service.CampaignService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@Controller
@RequiredArgsConstructor
public class CampaignController {
    final CampaignService campaignService;

    @RequestMapping("/create")
    public String getNewCampaignPage(){
        return "subPage/createNewCampaign";
    }

    @RequestMapping("/edit/{id}")
    public String getEditCampaignPage(){
        return "subPage/editCampaign";
    }

    @RequestMapping("/list")
    public String getCampaignList(){
        return "subPage/campaignList";
    }

    @PostMapping("/api/campaign")
    @ResponseBody
    public ResponseEntity<String> createCampaign(@RequestBody CampaignDto campaign) {
        return campaignService.createCampaign(campaign);
    }

    @PutMapping("/api/campaign/{id}")
    @ResponseBody
    public ResponseEntity<String> editCampaign(@PathVariable Long id, @RequestBody CampaignDto campaign) {
        return campaignService.editCampaign(id, campaign);
    }

    @DeleteMapping("/api/campaign/{id}")
    @ResponseBody
    public void deleteCampaign(@PathVariable Long id) {
        campaignService.deleteById(id);
    }

    @GetMapping("/api/campaign/{id}")
    @ResponseBody
    public Campaign getCampaignById(@PathVariable Long id) {
        return campaignService.findById(id);
    }

    @GetMapping("/api/campaign")
    @ResponseBody
    public List<Campaign> getAllCampaigns() {
        return campaignService.findAll();
    }
}
