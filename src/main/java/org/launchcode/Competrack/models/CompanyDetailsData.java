package org.launchcode.Competrack.models;

import org.launchcode.Competrack.data.CompanyDetailsRepository;
import org.launchcode.Competrack.data.IndustryRepository;
import org.launchcode.Competrack.data.SubindustryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class CompanyDetailsData {

   private static  ArrayList<CompanyDetails> allCompanyDetails=new ArrayList<>();
    private static ArrayList<Industry> allIndustries=new ArrayList<>();
    private static ArrayList<Subindustry> allSubindustries=new ArrayList<>();
    private static ArrayList<String> allLocations=new ArrayList<>();

    /*@Autowired
    private CompanyDetailsRepository companyDetailsRepository;*/

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private SubindustryRepository subindustryRepository;



    /*public ArrayList<CompanyDetails> findAll() {
        allCompanyDetails = (ArrayList<CompanyDetails>) companyDetailsRepository.findAll();
    return allCompanyDetails;
    }*/

    public ArrayList<CompanyDetails> findByColumnAndValue(String column, String value, CompanyDetailsRepository companyDetailsRepository) {


        ArrayList<CompanyDetails> companyDetails = new ArrayList<>();

        if (column.equals("company")){
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

        allCompanyDetails = (ArrayList<CompanyDetails>) companyDetailsRepository.findAll();
        ArrayList<CompanyDetails> companyDetails = new ArrayList<>();

        for (CompanyDetails companyDetail : allCompanyDetails) {


            if (companyDetail.getName().toLowerCase().contains(value.toLowerCase())) {
                companyDetails.add(companyDetail);
            } else if (companyDetail.getIndustry().toLowerCase().contains(value.toLowerCase())) {
                companyDetails.add(companyDetail);
            } else if (companyDetail.getSubindustry().contains(value.toLowerCase())) {
                companyDetails.add(companyDetail);
            } else if (companyDetail.getAddress().contains(value.toLowerCase())) {
                companyDetails.add(companyDetail);
            } else if (companyDetails.toString().toLowerCase().contains(value.toLowerCase())) {
                companyDetails.add(companyDetail);
            }

        }
        return companyDetails;
    }

    private static Object findExistingObject(ArrayList list, String value){
        for (Object item : list){
            if (item.toString().toLowerCase().equals(value.toLowerCase())){
                return item;
            }
        }
        return null;
    }

    public static ArrayList<Industry> getAllIndustries() {
        allIndustries.sort(new NameSorter());
        return allIndustries;
    }

    public static ArrayList<Subindustry> getAllSubindustries() {
        allSubindustries.sort(new NameSorter());
        return allSubindustries;
    }

    public static ArrayList<String> getAllLocations() {
        allLocations.sort(new NameSorter());
        return allLocations;
    }
}
