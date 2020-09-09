package com.task.tgbot.demo.service;

import com.task.tgbot.demo.dao.CityInfoRepository;
import com.task.tgbot.demo.entity.CityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CityService {
    @Autowired
    private CityInfoRepository repository;

    public CityInfo save(CityInfo entity){
        repository.save(entity);
        return entity;
    }

    public void delete(CityInfo entity){
        repository.delete(entity);
    }

    public void delete(String name){
        String id = name.toLowerCase();
        if (repository.existsById(id)){
            repository.deleteById(id);
        }
    }

    public List<CityInfo> findAll(){
        return repository.findAll();
    }

    public CityInfo findByName(String name){
        String id=name.toLowerCase();
        return repository.findById(id).orElse(null);
    }
}
