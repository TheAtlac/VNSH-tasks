package ru.nshi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//@RequestMapping(HealthController.MAPPING)
@RestController
public class HealthController {
    public final static String MAPPING  = "/ping";

    @GetMapping(value = MAPPING)
    public String ping() {
        return "pong";
    }
}

