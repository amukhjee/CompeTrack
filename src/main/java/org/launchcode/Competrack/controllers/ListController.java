package org.launchcode.Competrack.controllers;

import org.launchcode.Competrack.data.CompanyDetailsRepository;
import org.launchcode.Competrack.data.IndustryRepository;
import org.launchcode.Competrack.data.SubindustryRepository;
import org.launchcode.Competrack.data.UserRepository;
import org.launchcode.Competrack.models.CompanyDetails;
import org.launchcode.Competrack.models.CompanyDetailsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ListController {

    @Autowired
    private CompanyDetailsRepository companyDetailsRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private SubindustryRepository subindustryRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();



    public ListController () {
        columnChoices.put("all", "All");
        columnChoices.put("industry", "Industry");
        columnChoices.put("subindustry", "Subindustry");
        columnChoices.put("location", "Location");
    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("indsustry", industryRepository.findAll());
        model.addAttribute("subindustries", subindustryRepository.findAll());

        return "list";
    }

    @RequestMapping(value = "companyDetails")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<CompanyDetails> companyDetails=null;
        String industry;
        if (column.toLowerCase().equals("all")){
            companyDetails = companyDetailsRepository.findAll();
            model.addAttribute("title", "All Company");

        } else if (column.toLowerCase().equals("industry")){
            companyDetails = CompanyDetailsData.findByColumnAndValue(column, value, companyDetailsRepository.findAll());

            model.addAttribute("title", "Companies with " + columnChoices.get(column) + ": " + value);


        }
        else if (column.toLowerCase().equals("subindustry")){
            companyDetails= CompanyDetailsData.findByColumnAndValue(column, value, companyDetailsRepository.findAll());
            model.addAttribute("title", "Companies with " + columnChoices.get(column) + ": " + value);


        }

        model.addAttribute("companyDetails", companyDetails);
        return "list-companyDetails";
    }
}
