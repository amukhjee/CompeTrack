package org.launchcode.Competrack.data;

import org.launchcode.Competrack.models.CompanyDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CompanyDetailsData {
    private static final Map<Integer, CompanyDetails> companies= new HashMap<>();

    public static Collection<CompanyDetails> getAll(){
        return companies.values();

    }

    public static CompanyDetails getByID(int id){
        return companies.get(id);

    }

    public static void add(CompanyDetails companyDetails){
        companies.put(companyDetails.getId(), companyDetails);
    }

    public static void remove(int id){
        companies.remove(id);
    }
}
