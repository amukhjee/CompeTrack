package org.launchcode.Competrack.controllers;

import org.launchcode.Competrack.data.CompanyDetailsRepository;
import org.launchcode.Competrack.data.UserRepository;
import org.launchcode.Competrack.models.CompanyDetails;
import org.launchcode.Competrack.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class MyController {

    @Autowired
    private UserRepository userRepository;


        @GetMapping("register")
        public String renderRegistrationForm(Model model) {
            model.addAttribute("title", "Create User");
            model.addAttribute(new User());
            return "register";
        }

        @PostMapping("register")
        public String processRegistrationForm(@ModelAttribute User newUser, Errors errors, Model model) {

            userRepository.save(newUser);
            return "redirect:";
        }
    }


