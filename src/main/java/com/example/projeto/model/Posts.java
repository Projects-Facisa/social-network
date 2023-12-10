package com.example.projeto.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;
    private LocalDateTime date;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

//    @ManyToOne
//    private SocialUser user;
//
//    public SocialUser getUser() {
//        return user;
//    }
//
//    public void setUser(SocialUser user) {
//        this.user = user;
//    }
    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//
}