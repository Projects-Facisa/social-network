package com.example.projeto.controller;

import com.example.projeto.model.SocialUser;
import com.example.projeto.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjetoController {
    private UserRepository repository;

    public ProjetoController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/userlogin")
    public String registerUsers(@ModelAttribute SocialUser socialUser, Model model){
        model.addAttribute("socialUser", new SocialUser());
        repository.save(socialUser);
        return "index";
    }

    @GetMapping("/userlist")
    public String listUsers(Model model){
        model.addAttribute("socialUser", repository.findAll());
        return "userlist";
    }


}
