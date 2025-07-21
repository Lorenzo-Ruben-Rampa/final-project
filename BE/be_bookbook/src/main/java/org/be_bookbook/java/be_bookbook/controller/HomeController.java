package org.be_bookbook.java.be_bookbook.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
        @GetMapping
    public String home(Model model) {
        model.addAttribute("welcomeMessage", "Benvenuto nel Bibliotecarium!");
        return "home";
    } 
    
}
