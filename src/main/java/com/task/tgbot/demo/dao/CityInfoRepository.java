package com.task.tgbot.demo.dao;

import com.task.tgbot.demo.entity.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityInfoRepository extends JpaRepository<CityInfo, String> {
}
