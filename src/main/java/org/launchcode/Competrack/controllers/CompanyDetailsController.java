package org.launchcode.Competrack.controllers;


import org.launchcode.Competrack.data.CompanyDetailsData;
import org.launchcode.Competrack.models.CompanyDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("companyDetails")
public class CompanyDetailsController {


    @GetMapping
    public String displayallcompanydetails(Model model) {
        model.addAttribute("title", "All Company Details");
        model.addAttribute("companyDetails", CompanyDetailsData.getAll());
        return "companyDetails/index";

    }

    @GetMapping("create")
    public String renderCreateCompanyDetailsForm(Model model) {
        model.addAttribute("title", "Create Company");
        return "companyDetails/create";
    }

    @PostMapping("create")
    public String processCreateCompanyDetailsForm(@RequestParam String companyName, @RequestParam String industry) {
        CompanyDetailsData.add(new CompanyDetails(companyName, industry));
        return "redirect:";
    }

    @GetMapping("delete")
    public String deleteCompanyDetailsForm(Model model) {
        model.addAttribute("title", "Delete Company");
        model.addAttribute("companyDetails", CompanyDetailsData.getAll());
        return "companyDetails/delete";
    }

    @PostMapping("delete")
    public String processDeleteCompanyForm(@RequestParam(required = false) int[] companyDetailIds){
        if(companyDetailIds!=null) {
            for (int id : companyDetailIds)
                CompanyDetailsData.remove(id);
        }
        return "redirect:";
    }
}
