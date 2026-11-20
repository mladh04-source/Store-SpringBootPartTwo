package com.storeapi.controllers;

import com.storeapi.entities.Massage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MassageController {

    @RequestMapping("/hello")
    public Massage sayHallo() {
        return new Massage("Hi");
    }

}
