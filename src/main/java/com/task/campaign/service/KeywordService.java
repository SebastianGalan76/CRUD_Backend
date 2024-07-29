package com.task.campaign.service;

import com.task.campaign.database.model.City;
import com.task.campaign.database.model.Keyword;
import com.task.campaign.database.repository.KeywordRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class KeywordService {
    final KeywordRepository keywordRepository;

    @Cacheable(value = "keywordsByNamePrefix", key = "#prefix")
    public List<Keyword> findByPrefix(String prefix){
        return keywordRepository.findByNameStartingWithIgnoreCase(prefix.toLowerCase());
    }

    public List<Keyword> getAll() {
        return keywordRepository.findAll();
    }
}
