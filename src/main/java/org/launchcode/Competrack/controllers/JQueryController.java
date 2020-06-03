package org.launchcode.Competrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("query")
public class JQueryController {
    @GetMapping
    public String JQueryproccesor(Model model) {
        return "greeting.js";
    }
}
