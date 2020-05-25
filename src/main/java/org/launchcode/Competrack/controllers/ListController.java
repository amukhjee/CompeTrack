package org.launchcode.Competrack.controllers;

import org.launchcode.Competrack.data.CompanyDetailsRepository;
import org.launchcode.Competrack.data.IndustryRepository;
import org.launchcode.Competrack.data.SubindustryRepository;
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
@RequestMapping(value = "list")
public class ListController extends CompanyDetailsActionController{

    @Autowired
    private CompanyDetailsRepository companyDetailsRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private SubindustryRepository subindustryRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();
    static HashMap<String, Object> tableChoices = new HashMap<>();
    static List<String> companyDetailsListHeads= new ArrayList<>();



    public ListController () {
        columnChoices.put("company", "CompanyDetails");
        columnChoices.put("industry", "Industry");
        columnChoices.put("subindustry", "Subindustry");
        columnChoices.put("location", "Address");

        tableChoices.put("industry", CompanyDetailsData.getAllIndustries());
        tableChoices.put("subindustry", CompanyDetailsData.getAllSubindustries());
        tableChoices.put("location", CompanyDetailsData.getAllLocations());

        companyDetailsListHeads.add("ID");
        companyDetailsListHeads.add("Name");
        companyDetailsListHeads.add("Industry");
        companyDetailsListHeads.add("Subindustry");
        companyDetailsListHeads.add("Location");
    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("tableChoices", tableChoices);
        model.addAttribute("actions", actionChoices);
        model.addAttribute("companyDetails", companyDetailsRepository.findAll());
        model.addAttribute("industries", industryRepository.findAll());
        model.addAttribute("subindustries", subindustryRepository.findAll());

        return "list";
    }

    @RequestMapping(value = "companyDetails")
    public String listCompanyDetailsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        model.addAttribute("companyDetails", companyDetailsRepository.findAll());
        model.addAttribute("industries", industryRepository.findAll());
        model.addAttribute("subindustries", subindustryRepository.findAll());

        ArrayList<CompanyDetails> companyDetails;
        CompanyDetailsData companyDetails1 = new CompanyDetailsData();
            companyDetails = findByColumnAndValue(column, value, companyDetailsRepository);
            model.addAttribute("title", "Companies with " + columnChoices.get(column) + ": " + value);
        model.addAttribute("companyDetails", companyDetails);
        model.addAttribute("companyDetailsListHeads", companyDetailsListHeads);
        model.addAttribute("actions", actionChoices);

        return "list-companyDetails";
    }

    public ArrayList<CompanyDetails> findByColumnAndValue(String column, String value, CompanyDetailsRepository companyDetailsRepository) {


        ArrayList<CompanyDetails> allCompanyDetails=new ArrayList<>();
        ArrayList<CompanyDetails> companyDetails = new ArrayList<>();
        allCompanyDetails = (ArrayList<CompanyDetails>) companyDetailsRepository.findAll();

        if ("subindustry".equals(column)|| "industry".equals(column)){
            companyDetails = findByValue(value,companyDetailsRepository);
            return companyDetails;
        }

        for (CompanyDetails companyDetail : allCompanyDetails) {

            String aValue = getFieldValue(companyDetail, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                companyDetails.add(companyDetail);
            }
        }

        return companyDetails;
    }

    public String getFieldValue(CompanyDetails companyDetails, String fieldName){
        String theValue;
        if (fieldName.equals("company")){
            theValue = companyDetails.getName();
        } else if (fieldName.equals("industry")){
            theValue = companyDetails.getIndustry().toString();
        }else if (fieldName.equals("subindustry")){
            theValue = companyDetails.getSubindustry().toString();
        }else if (fieldName.equals("location")){
            theValue = companyDetails.getAddress();
        } else {
            theValue = companyDetails.toString();
        }

        return theValue;
    }

    public ArrayList<CompanyDetails> findByValue(String value, CompanyDetailsRepository companyDetailsRepository){

        ArrayList<CompanyDetails> allCompanyDetails=new ArrayList<>();
        allCompanyDetails = (ArrayList<CompanyDetails>) companyDetailsRepository.findAll();

        ArrayList<CompanyDetails> companyDetails = new ArrayList<>();

        for (CompanyDetails companyDetail : allCompanyDetails) {


            if (companyDetail.getName() != null && companyDetail.getName().toLowerCase().contains(value.toLowerCase())) {
                companyDetails.add(companyDetail);
            } else if (companyDetail.getIndustry() != null && companyDetail.getIndustry().toLowerCase().contains(value.toLowerCase())) {
                companyDetails.add(companyDetail);
            } else if (companyDetail.getSubindustry()!=null && companyDetail.getSubindustry().contains(value.toLowerCase())) {
                companyDetails.add(companyDetail);
            } else if (companyDetail.getAddress()!=null && companyDetail.getAddress().contains(value.toLowerCase())) {
                companyDetails.add(companyDetail);
            } else if (companyDetails.toString().toLowerCase().contains(value.toLowerCase())) {
                companyDetails.add(companyDetail);
            }

        }
        return companyDetails;
    }
}
