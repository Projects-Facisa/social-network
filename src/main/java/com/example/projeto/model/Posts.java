package com.example.projeto.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;
    private Date postDate;

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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
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