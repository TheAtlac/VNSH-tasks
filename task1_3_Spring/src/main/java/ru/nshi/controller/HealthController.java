package ru.nshi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nshi.model.Status;


//@RequestMapping(HealthController.MAPPING)
@RestController
public class HealthController {
    public final static String MAPPING  = "/ping";

    @GetMapping(value = "/ping", produces = "application/json")
    public String ping() {
        System.out.println("try ping");
        return "{\"status\" = \"UP\"}";
    }
}
