package org.launchcode.Competrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Helloworld {
    @ResponseBody
    @GetMapping
    public String hello(){
        return "Hello Arpita!";
    }
}
