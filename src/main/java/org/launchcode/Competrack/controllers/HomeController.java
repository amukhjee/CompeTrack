package org.launchcode.Competrack.controllers;

import org.launchcode.Competrack.data.MessageRepository;
import org.launchcode.Competrack.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("msgs", messageRepository.findAll());
        return "home";
    }

    @PostMapping("messages")
    public String saveMessage(Message message) {
        messageRepository.save(message);
        return "redirect:/home";
    }
}
