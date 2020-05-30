package org.launchcode.Competrack.controllers;

import org.launchcode.Competrack.data.CompanyDetailsRepository;
import org.launchcode.Competrack.models.CompanyDetails;
import org.launchcode.Competrack.models.CompanyDetailsData;
import org.launchcode.Competrack.models.DTO.CompanyDetailsSubindustryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.launchcode.Competrack.controllers.ListController.columnChoices;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private CompanyDetailsRepository companyDetailsRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<CompanyDetails> companyDetails;
        if (searchTerm.toLowerCase().equals("") || searchTerm.equals("")){
            companyDetails = companyDetailsRepository.findAll();
        } else {
            CompanyDetailsData companyDetails1 = new CompanyDetailsData();
            companyDetails = companyDetails1.findByColumnAndValue(searchType, searchTerm,companyDetailsRepository);
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Companies with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("companyDetails", companyDetails);

        return "list-companyDetails";
    }
}
