package com.example.projeto.repository;
import com.example.projeto.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Integer> {
    List<Posts> findAllByOrderByDateDesc();
}
