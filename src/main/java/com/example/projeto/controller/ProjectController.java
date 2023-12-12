package com.example.projeto.controller;

import com.example.projeto.model.Posts;
import com.example.projeto.model.SocialUser;
import com.example.projeto.repository.PostsRepository;
import com.example.projeto.repository.UserRepository;
import com.example.projeto.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {
    private UserRepository userRepository;
    private PostsRepository postsRepository;

    public ProjectController() {
    }

    @Autowired
    public ProjectController(UserRepository userRepository, PostsRepository postsRepository) {
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
    }

    @GetMapping
    public String index(){
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("socialUser", new SocialUser());
        return "index";
    }
    @PostMapping("/login")
    public String usersLogin(@ModelAttribute SocialUser socialUser, Model model, HttpServletResponse response){
        SocialUser user = this.userRepository.Login(socialUser.getUsername(), socialUser.getPassword());
        if (user != null){
            CookieService.setCookie(response, "userId", String.valueOf(user.getId()),9999999);
            model.addAttribute("error", null);
            return "redirect:/home";
        }
        model.addAttribute("error", "wrong password or username");
        return "index";
    }
    @GetMapping("/login/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("socialUser", new SocialUser());
        return "index";
    }

    @PostMapping("/login/register")
    public String registerUsers(@ModelAttribute SocialUser socialUser, Model model){
        SocialUser newUser = this.userRepository.RegisterCheck(socialUser.getUsername());
        if (newUser == null) {
            userRepository.save(socialUser);
        } else{
            model.addAttribute("inUse", "Username already in use");
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        List<Posts> sortedPosts = postsRepository.findAllByOrderByDateDesc();
        model.addAttribute("posts", sortedPosts);

        return "home";
    }


    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("post", new Posts());
        return "post";
    }
    @PostMapping("/post")
    public String createPost(@ModelAttribute Posts post, Model model,  HttpServletRequest request) {
        model.addAttribute("post", post);
        String userId = CookieService.getCookie(request, "userId");
        if (userId != null) {
            Optional<SocialUser> user = userRepository.findById(Integer.parseInt(userId));
            if(user.isPresent()) {
                post.setUser(user.get());
                postsRepository.save(post);
                return "redirect:/home";
            }
        }
        return "post";
    }
    @GetMapping("/search")
    public String search(){
        return "search";
    }
    @GetMapping("/exit")
    public String logout(HttpServletResponse response) {
    CookieService.setCookie(response, "userId", "", 0);
    return "redirect:/login";
    }
}