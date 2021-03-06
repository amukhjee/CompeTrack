package org.launchcode.Competrack.controllers;


import org.launchcode.Competrack.data.IndustryRepository;
import org.launchcode.Competrack.data.SubindustryRepository;
import org.launchcode.Competrack.models.Industry;
import org.launchcode.Competrack.models.Subindustry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("subindustry")
public class SubindustryController {
    @Autowired
    private SubindustryRepository subindustryRepository;

    @GetMapping("add")
    public String displayAddSubindustryForm(Model model) {
        model.addAttribute("subindustry", new Subindustry());
        model.addAttribute("subindustries", subindustryRepository.findAll());
        return "subindustry/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Subindustry newSubindustry,
                                         Errors errors, Model model, @RequestParam(required = false) Integer subindustryId) {

        if (errors.hasErrors()) {
            return "subindustry/add";
        }
        subindustryRepository.save(newSubindustry);
        model.addAttribute("subindustries", subindustryRepository.findAll());
        return "subindustry/view";
    }

    @GetMapping("view")
    public String viewIndustries(Model model) {

        model.addAttribute("subindustries", subindustryRepository.findAll());
        return "subindustry/view";


    }
    }

