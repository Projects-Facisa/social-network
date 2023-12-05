package com.example.projeto.controller;

import com.example.projeto.model.SocialUser;
import com.example.projeto.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjetoController {
    private UserRepository repository;

    public ProjetoController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/userlogin")
    public String showLoginForm(Model model) {
        model.addAttribute("socialUser", new SocialUser());
        return "index";
    }
    @PostMapping("/userlogin")
    public String UsersLogin(@ModelAttribute SocialUser userLogin, Model model){
        SocialUser user = this.repository.Login(userLogin.getUsername(), userLogin.getPassword());
        if (user != null){
            model.addAttribute("error", null);
            return "redirect:/home";
        }
        model.addAttribute("error", "wrong password or username");
        return "index";
    }
    @RequestMapping("/registeruser")
    public String RegisterUsers(@ModelAttribute SocialUser socialUser, Model model){
        repository.save(socialUser);
        return "index";
    }

    @GetMapping("/userlist")
    public String ListUsers(Model model){
        model.addAttribute("socialUser", repository.findAll());
        return "userlist";
    }

    @GetMapping("/home")
    public String SocialNetworkHome(Model model){
        return "home";
    }




}
