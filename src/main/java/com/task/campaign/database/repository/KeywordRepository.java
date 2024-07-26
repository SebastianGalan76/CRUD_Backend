package com.task.campaign.database.repository;

import com.task.campaign.database.model.City;
import com.task.campaign.database.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findByNameStartingWithIgnoreCase(String prefix);
}
