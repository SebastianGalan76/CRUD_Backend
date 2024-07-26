package com.task.campaign.database.repository;

import com.task.campaign.database.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByNameStartingWithIgnoreCase(String prefix);
}
