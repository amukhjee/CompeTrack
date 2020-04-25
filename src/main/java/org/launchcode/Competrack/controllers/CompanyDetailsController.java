package org.launchcode.Competrack.controllers;

import org.launchcode.Competrack.models.CompanyDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("companyDetails")
public class CompanyDetailsController {
    private static List<CompanyDetails> companyDetails= new ArrayList<>();

    @GetMapping
    public String displayallcompanydetails(Model model){
        model.addAttribute("title", "All Company Details");
        model.addAttribute("companyDetails", companyDetails);
        return "companyDetails/index";

    }
@GetMapping("create")
    public String renderCreateCompanyDetailsForm(Model model){
        return "companyDetails/create";
    }
}
