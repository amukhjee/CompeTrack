package org.launchcode.Competrack.controllers;

import org.launchcode.Competrack.data.IndustryRepository;
import org.launchcode.Competrack.models.Industry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("industry")
public class IndustryController {

    @Autowired
    private IndustryRepository industryRepository;

    @GetMapping("add")
    public String displayAddIndustryForm(Model model) {
        model.addAttribute("industry", new Industry());
        model.addAttribute("industries", industryRepository.findAll());
        return "industry/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Industry newIndustry,
                                         Errors errors, Model model, @RequestParam(required = false) Integer industryId) {

        if (errors.hasErrors()) {
            return "industry/add";
        }
        industryRepository.save(newIndustry);
        return "redirect:../";


    }

    @GetMapping("view/{industryId}")
    public String displayViewEmployer(Model model, @PathVariable int industryId ) {

        Optional optIndustry = industryRepository.findById(industryId);
        if (optIndustry.isPresent()) {
            Industry industry = (Industry) optIndustry.get();
            model.addAttribute("industry", industry);
            return "industry/view";
        } else {
            return "industry/add";
        }
    }
}
