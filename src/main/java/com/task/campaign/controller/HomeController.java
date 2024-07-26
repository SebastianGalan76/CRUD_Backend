package com.task.campaign.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Controller
@RequiredArgsConstructor
public class HomeController {

    @RequestMapping("/")
    public String getHomePage(){
        return "home";
    }

    @RequestMapping("/new-campaign")
    public String getNewCampaignName(){
        return "subPage/createNewCampaign";
    }
}
