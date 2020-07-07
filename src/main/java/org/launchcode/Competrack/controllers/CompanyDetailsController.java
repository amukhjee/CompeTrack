package org.launchcode.Competrack.controllers;



import org.launchcode.Competrack.data.CompanyDetailsRepository;
import org.launchcode.Competrack.data.IndustryRepository;
import org.launchcode.Competrack.data.SubindustryRepository;
import org.launchcode.Competrack.models.CompanyDetails;
import org.launchcode.Competrack.models.Industry;
import org.launchcode.Competrack.models.Subindustry;
import org.launchcode.Competrack.models.User;
import org.launchcode.Competrack.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("companyDetails")
public class CompanyDetailsController {

    @Autowired
    private CompanyDetailsRepository companyDetailsRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private SubindustryRepository subindustryRepository;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("welcome")
    public String displayallcompanydetailsWithWelcome(Model model, @RequestParam String username) {

        ArrayList<CompanyDetails> companyDetails = (ArrayList<CompanyDetails>) companyDetailsRepository.findAll();

        ServiceResponse response;
        for (CompanyDetails companyDetail : companyDetails) {
            response = new ServiceResponse();
            response = restServiceInvoker(companyDetail.getName());
            if ("SUCCESS".equalsIgnoreCase(response.getResponseType())) {
                companyDetail.setRevenue(String.valueOf(response.getFinance().getRevenue()));
                companyDetail.setEarnings(String.valueOf(response.getFinance().getEarnings()));
            } else {
                companyDetail.setEarnings("No data available");
                companyDetail.setRevenue("No data avaialble");
            }
        }
        model.addAttribute("title", "All Company Details");
        model.addAttribute("companyDetails", companyDetails);
        // ArrayList<CompanyDetails> allCompanyDetails = (ArrayList<CompanyDetails>) companyDetailsRepository.findAll();
        model.addAttribute("industries", industryRepository.findAll());
        model.addAttribute("subindustries", subindustryRepository.findAll());
        model.addAttribute("username", username);
        return "companyDetails/welcomeindex";
    }


        @GetMapping
        public String displayallcompanydetails(Model model, @RequestParam String username) {

            ArrayList <CompanyDetails> companyDetails = (ArrayList<CompanyDetails>) companyDetailsRepository.findAll();

            ServiceResponse response;
            for(CompanyDetails companyDetail:companyDetails)
            {
                response = new ServiceResponse();
                response = restServiceInvoker(companyDetail.getName());
                if("SUCCESS".equalsIgnoreCase(response.getResponseType()))
                {
                    companyDetail.setRevenue(String.valueOf(response.getFinance().getRevenue()));
                    companyDetail.setEarnings(String.valueOf(response.getFinance().getEarnings()));
                }else
                {
                    companyDetail.setEarnings("No data available");
                    companyDetail.setRevenue("No data avaialble");
                }
            }
        model.addAttribute("title", "All Company Details");
        model.addAttribute("companyDetails", companyDetails);
       // ArrayList<CompanyDetails> allCompanyDetails = (ArrayList<CompanyDetails>) companyDetailsRepository.findAll();
       model.addAttribute("industries", industryRepository.findAll());
        model.addAttribute("subindustries", subindustryRepository.findAll());
        model.addAttribute("username", username);
        return "companyDetails/index";

    }

    @GetMapping("create")
    public String renderCreateCompanyDetailsForm(Model model) {
        model.addAttribute("title", "Create Company");
        model.addAttribute(new CompanyDetails());
        model.addAttribute("industries", industryRepository.findAll());
        model.addAttribute("subindustries", subindustryRepository.findAll());
        return "companyDetails/create";
    }

    @PostMapping("create")
    public String processCreateCompanyDetailsForm(@ModelAttribute @Valid CompanyDetails newCompanyDetails, Errors errors, Model model, @RequestParam @Valid String industry, @RequestParam String subindustry, @RequestParam String url, @RequestParam String address, @RequestParam String name) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Company");
            model.addAttribute("industries", industryRepository.findAll());
            model.addAttribute("subindustries", subindustryRepository.findAll());
            return "companyDetails/create";
        } else {
            // Optional optIndustry = industryRepository.findById(industryId);
            // if (optIndustry.isPresent()) {
            // Industry industry = (Industry) optIndustry.get();
            newCompanyDetails.setName(name);
            newCompanyDetails.setUrl(url);
            newCompanyDetails.setAddress(address);
            newCompanyDetails.setIndustry(industry);
            newCompanyDetails.setSubindustry(subindustry);
            companyDetailsRepository.save(newCompanyDetails);

            return "companyDetails/index";
        }
    }


    @GetMapping("view/{companyDetailsId}")
    public String displayViewCompanyDetails(Model model, @PathVariable int companyDetailsId) {
        Optional<CompanyDetails> result= companyDetailsRepository.findById(companyDetailsId);
        CompanyDetails companyDetails=(CompanyDetails) result.get();
        model.addAttribute("companyDetails", companyDetails);

        return "view";
    }


    @GetMapping("delete")
    public String deleteCompanyDetailsForm(Model model) {
        model.addAttribute("title", "Delete Company");
        model.addAttribute(new CompanyDetails());
        model.addAttribute("industries", industryRepository.findAll());
        model.addAttribute("subindustries", subindustryRepository.findAll());
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
        model.addAttribute("industries", industryRepository.findAll());
        model.addAttribute("subindustries", subindustryRepository.findAll());
        return "companyDetails/update";
    }

    @PostMapping("update")
    public String processUpdateCompanyDetailsForm(  @ModelAttribute @Valid CompanyDetails newcompanyDetails, Errors errors, Model model, @RequestParam @Valid String industry, @RequestParam String subindustry, @RequestParam String url, @RequestParam String address, @RequestParam String name ) {
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

    public ServiceResponse restServiceInvoker(String name)
    {
        RestTemplateBuilder restBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restBuilder.build();
        ServiceResponse response = restTemplate.getForObject("http://localhost:8083/finance?name="+name, ServiceResponse.class);
        return response;
    }


}



