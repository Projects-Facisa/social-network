package com.example.projeto.repository;
import com.example.projeto.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PostsRepository extends JpaRepository<Posts, Integer> {

}
