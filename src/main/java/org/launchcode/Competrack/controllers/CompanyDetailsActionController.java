package org.launchcode.Competrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.HashMap;

import static org.launchcode.Competrack.controllers.ListController.columnChoices;

@Controller
public class CompanyDetailsActionController {
    public static HashMap<String, String > actionChoices=new HashMap<>();

    public CompanyDetailsActionController(){
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

    }

    public static String getActionChoices(Model model){
        model.addAttribute("actions", actionChoices);
        model.addAttribute("columns", columnChoices);
        return "actions";
    }
}
