package org.launchcode.Competrack.controllers;



import com.sun.xml.bind.v2.model.core.ID;
import org.launchcode.Competrack.data.CompanyDetailsRepository;
import org.launchcode.Competrack.models.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.persistence.Id;
import javax.swing.*;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
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
        model.addAttribute(new CompanyDetails());
        return "companyDetails/create";
    }

    @PostMapping("create")
    public String processCreateCompanyDetailsForm(@ModelAttribute @Valid CompanyDetails newCompanyDetails, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Company");
            return "companyDetails/create";
        }
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
    public String processDeleteCompanyForm(@RequestParam(required = false) int[] companyDetailIds) {
        if (companyDetailIds != null) {
            for (int id : companyDetailIds)
                companyDetailsRepository.deleteById(id);
        }
        return "redirect:";
    }

    @GetMapping("map")
    public String getCompanyLocationMap(Model model,@RequestParam String search) {
        model.addAttribute("title", "Company Location");
        //model.addAttribute("companyDetails", companyDetailsRepository.findAll());
        //companyDetailsRepository.fi(search);
        return "companyDetails/map";
    }


    @PostMapping("map")
    public String processCompanyLocationMap(@RequestParam int companyDetailId) {
       companyDetailsRepository.findById(companyDetailId);
        return "redirect:";
    }

}



