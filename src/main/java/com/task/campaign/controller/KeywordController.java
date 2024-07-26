package com.task.campaign.controller;

import com.task.campaign.database.model.City;
import com.task.campaign.database.model.Keyword;
import com.task.campaign.service.CityService;
import com.task.campaign.service.KeywordService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/keyword")
@RequiredArgsConstructor
public class KeywordController {
    final KeywordService keywordService;

    @GetMapping
    public List<Keyword> getAllKeywords(){
        return keywordService.getAll();
    }

    @GetMapping("/search")
    public List<Keyword> findByPrefix(@RequestParam String prefix){
        return keywordService.findByPrefix(prefix);
    }
}
