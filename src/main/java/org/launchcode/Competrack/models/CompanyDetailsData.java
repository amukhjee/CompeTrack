package org.launchcode.Competrack.models;

import java.util.ArrayList;

public class CompanyDetailsData {

    private static java.util.List<Subindustry> subindustries;

    public static ArrayList<CompanyDetails> findByColumnAndValue(String column, String value, Iterable<CompanyDetails> allCompanyDetails) {

        ArrayList<CompanyDetails> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<CompanyDetails>) allCompanyDetails;
        }

        if (column.equals("all")){
            results = findByValue(value, allCompanyDetails);
            return results;
        }
        for (CompanyDetails companyDetails : allCompanyDetails) {

            String aValue = getFieldValue(companyDetails, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(companyDetails);
            }
        }

        return results;
    }

    public static String getFieldValue(CompanyDetails companyDetails, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = companyDetails.getName();
        } else if (fieldName.equals("industry")){
            theValue = companyDetails.getIndustry().toString();
        }else if (fieldName.equals("subindustry")){
            theValue = companyDetails.getSubindustries().toString();
        } else {
            theValue = companyDetails.toString();
        }

        return theValue;
    }

    public static ArrayList<CompanyDetails> findByValue(String value, Iterable<CompanyDetails> allCompanyDetails) {


        ArrayList<CompanyDetails> results = new ArrayList<>();

        for (CompanyDetails companyDetails : allCompanyDetails) {


            if (companyDetails.getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(companyDetails);
            } else if (companyDetails.getIndustry().getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(companyDetails);
            } else if (companyDetails.getSubindustries().contains(value.toLowerCase())) {
                results.add(companyDetails);
            } else if (companyDetails.toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(companyDetails);
            }

        }
        return results;
    }

}
