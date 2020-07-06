package org.launchcode.Competrack.controllers;

import org.launchcode.Competrack.data.CompanyDetailsRepository;
import org.launchcode.Competrack.data.IndustryRepository;
import org.launchcode.Competrack.data.SubindustryRepository;
import org.launchcode.Competrack.data.UserRepository;
import org.launchcode.Competrack.models.CompanyDetails;
import org.launchcode.Competrack.models.DTO.LoginFormDTO;
import org.launchcode.Competrack.models.DTO.RegisterFormDTO;
import org.launchcode.Competrack.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RequestMapping("/")
@Controller
public class AuthenticationController {
    @Autowired
    private CompanyDetailsRepository companyDetailsRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private SubindustryRepository subindustryRepository;

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }


    @GetMapping("companyDetails")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        model.addAttribute("title", "All Company Details");
        model.addAttribute("companyDetails", companyDetailsRepository.findAll());
        ArrayList<CompanyDetails> allCompanyDetails = (ArrayList<CompanyDetails>) companyDetailsRepository.findAll();
        model.addAttribute("industries", industryRepository.findAll());
        model.addAttribute("subindustries", subindustryRepository.findAll());
        return "companyDetails/index";

    }

   /* @PostMapping("login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model, @RequestParam String username, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);
        redirectAttributes.addAttribute("username", username);
        return "redirect:companyDetails/index";
    }
*/
    @GetMapping("register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:login";
    }


    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "/logout";
    }

    @GetMapping("contact")
    public String getContact(Model model) {
        return "contact";
    }

    @GetMapping("aboutUs")
    public String getAboutUs(Model model) {
        return "aboutUs";
    }


}
