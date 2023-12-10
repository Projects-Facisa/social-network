package com.example.projeto.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;

//    @OneToMany
//    private List<Posts> posts = new ArrayList<>();
//
//    public List<Posts> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(ArrayList<Posts> posts) {
//        this.posts = posts;
//    }
    public void post(Posts content){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
