package com.task.tgbot.demo.controller;

import com.task.tgbot.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class CityController {

    @Autowired
    private CityService service;

    @GetMapping("/manager")
    public String showpage(Map<String, Object> model) {
        model.put("cities", service.findAll());
        return "manager";
    }
}
