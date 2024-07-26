package com.task.campaign.service;

import com.task.campaign.database.model.City;
import com.task.campaign.database.repository.CityRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class CityService {
    final CityRepository cityRepository;

    @Cacheable(value = "citiesByNamePrefix", key = "#prefix")
    public List<City> findByPrefix(String prefix){
        return cityRepository.findByNameStartingWithIgnoreCase(prefix.toLowerCase());
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }
}
