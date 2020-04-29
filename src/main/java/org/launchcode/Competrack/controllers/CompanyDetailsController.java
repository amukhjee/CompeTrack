package org.launchcode.Competrack.controllers;



import org.launchcode.Competrack.data.CompanyDetailsRepository;
import org.launchcode.Competrack.models.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("companyDetails")
public class CompanyDetailsController {

    @Autowired
    private CompanyDetailsRepository companyDetailsRepository;


    @GetMapping
    public String displayallcompanydetails(Model model) {
        model.addAttribute("title", "All Company Details");
        model.addAttribute("companyDetails", companyDetailsRepository.findAll());
        return "companyDetails/index";

    }

    @GetMapping("create")
    public String renderCreateCompanyDetailsForm(Model model) {
        model.addAttribute("title", "Create Company");
        return "companyDetails/create";
    }

    @PostMapping("create")
    public String processCreateCompanyDetailsForm(@ModelAttribute CompanyDetails newCompanyDetails) {
        companyDetailsRepository.save(newCompanyDetails);
        return "redirect:";
    }

    @GetMapping("delete")
    public String deleteCompanyDetailsForm(Model model) {
        model.addAttribute("title", "Delete Company");
        model.addAttribute("companyDetails", companyDetailsRepository.findAll());
        return "companyDetails/delete";
    }

    @PostMapping("delete")
    public String processDeleteCompanyForm(@RequestParam(required = false) int[] companyDetailIds){
        if(companyDetailIds!=null) {
            for (int id : companyDetailIds)
                companyDetailsRepository.deleteById(id);
        }
        return "redirect:";
    }
}
