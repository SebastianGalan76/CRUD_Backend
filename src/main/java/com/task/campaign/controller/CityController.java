package com.task.campaign.controller;

import com.task.campaign.database.model.City;
import com.task.campaign.service.CityService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {
    final CityService cityService;

    @GetMapping
    public List<City> getAllCities(){
        return cityService.getAll();
    }

    @GetMapping("/search")
    public List<City> findByPrefix(@RequestParam String prefix){
        return cityService.findByPrefix(prefix);
    }
}
