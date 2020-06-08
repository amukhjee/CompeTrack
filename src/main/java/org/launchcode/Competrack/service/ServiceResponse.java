package org.launchcode.Competrack.service;

public class ServiceResponse {
    private String responseType ;
    private Finance finance = new Finance();

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}
