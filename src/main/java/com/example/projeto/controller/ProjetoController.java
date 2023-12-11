package com.example.projeto.controller;

import com.example.projeto.model.Posts;
import com.example.projeto.model.SocialUser;
import com.example.projeto.repository.PostsRepository;
import com.example.projeto.repository.UserRepository;
import com.example.projeto.service.CookieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ProjetoController {
    private UserRepository userRepository;
    private PostsRepository postsRepository;

    public ProjetoController() {
    }

    @Autowired
    public ProjetoController(UserRepository userRepository, PostsRepository postsRepository) {
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
    }

    @GetMapping
    public String index(){
        return "redirect:/userlogin";
    }
    @GetMapping("/userlogin")
    public String showLoginForm(Model model) {
        model.addAttribute("socialUser", new SocialUser());
        return "index";
    }
    @PostMapping("/userlogin")
    public String UsersLogin(@ModelAttribute SocialUser userLogin, Model model, HttpServletResponse response){
        SocialUser user = this.userRepository.Login(userLogin.getUsername(), userLogin.getPassword());
        if (user != null){
            CookieService.setCookie(response, "userId", String.valueOf(user.getId()),9999999);
            model.addAttribute("error", null);
            return "redirect:/home";
        }
        model.addAttribute("error", "wrong password or username");
        return "index";
    }
    @GetMapping("/userlogin/registeruser")
    public String showRegisterForm(Model model) {
        model.addAttribute("socialUser", new SocialUser());
        return "index";
    }

    @PostMapping("/userlogin/registeruser")
    public String RegisterUsers(@ModelAttribute SocialUser socialUser, Model model){
        SocialUser newUser = this.userRepository.RegisterCheck(socialUser.getUsername());
        if (newUser == null) {
            userRepository.save(socialUser);
        } else{
            model.addAttribute("inUse", "Username already in use");
        }
        return "index";
    }
    @PostMapping("/home")
    public String createPost(@ModelAttribute Posts post, Model model) {

        postsRepository.save(post);
        return "redirect:home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpServletResponse response) {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        model.addAttribute("post", new Posts());
        model.addAttribute("posts", postsRepository.findAll());
        return "home";
    }

    @GetMapping("/postpage")
    public String Post(Model model) {
        model.addAttribute("post", new Posts());
        return "postpage";
    }


    @GetMapping("/exit")
    public String logout(HttpServletResponse response) {
    CookieService.setCookie(response, "userId", "", 0);
    return "redirect:/login";
    }
}