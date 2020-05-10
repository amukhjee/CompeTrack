package org.launchcode.Competrack.controllers;



import org.launchcode.Competrack.data.CompanyDetailsRepository;
import org.launchcode.Competrack.models.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

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

    @GetMapping("update")
    public String updateCompanyDetailsForm(Model model, @RequestParam int search) {
        model.addAttribute("companyDetails", companyDetailsRepository.findById(search));
        return "companyDetails/update";
    }

    @PostMapping("update")
    public String processUpdateCompanyDetailsForm(  @ModelAttribute @Valid CompanyDetails newcompanyDetails, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Update Company");
            return "companyDetails/update";
        }
        //companyDetailsRepository.deleteById(companyDetailIds);
        companyDetailsRepository.save(newcompanyDetails);
        return "redirect:";
    }


    @GetMapping("map")
    public String getCompanyLocationMap(Model model,@RequestParam String search) {
        model.addAttribute("title", "Company Location");
        return "companyDetails/map";
    }




}



