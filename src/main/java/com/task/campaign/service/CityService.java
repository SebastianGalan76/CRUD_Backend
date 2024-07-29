package com.task.campaign.service;

import com.task.campaign.database.model.City;
import com.task.campaign.database.repository.CityRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class CityService {
    final CityRepository cityRepository;

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public City getCityByName(String name){
        return cityRepository.findByNameIgnoreCase(name).orElse(null);
    }
}
