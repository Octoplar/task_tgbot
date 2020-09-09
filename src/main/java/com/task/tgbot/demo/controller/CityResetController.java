package com.task.tgbot.demo.controller;

import com.task.tgbot.demo.entity.CityInfo;
import com.task.tgbot.demo.logger.Log;
import com.task.tgbot.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manager")
public class CityResetController {
    @Autowired
    private CityService service;

    @GetMapping("/cities")
    public List<CityInfo> getAll() {
        Log.info("GET mapping: all");
        return service.findAll();
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<CityInfo> getByName(@PathVariable(value = "id") String name) {
        Log.info("GET mapping: name=%s", name);
        CityInfo result = service.findByName(name);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @PostMapping("/cities")
    public CityInfo createEmployee(@Valid @RequestBody CityInfo entity) {
        Log.info("POST mapping: entity=%s", entity);
        return service.save(entity);
    }


    @DeleteMapping("/cities/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") String name) {
        Log.info("DLETE mapping: name=%s", name);
        CityInfo result = service.findByName(name);
        Map<String, Boolean> response = new HashMap<>();
        if (result == null) {
            response.put("deleted", Boolean.FALSE);
        } else {
            service.delete(name);
            response.put("deleted", Boolean.TRUE);
        }
        return response;
    }
}