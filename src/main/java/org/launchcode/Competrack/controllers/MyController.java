package org.launchcode.Competrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MyController {

    public class MyContoller {

        @GetMapping("")
        public String index(Model model, Principal principal) {
            model.addAttribute("message", "You are logged in as " + principal.getName());
            return "index";
        }
    }
}
