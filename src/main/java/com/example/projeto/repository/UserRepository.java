package com.example.projeto.repository;
import com.example.projeto.model.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SocialUser, Integer> {
}
