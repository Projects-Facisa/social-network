package com.example.projeto.repository;
import com.example.projeto.model.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<SocialUser, Integer> {
    @Query(value = "select * from Social_User where username = :username and password = :password", nativeQuery = true)
    public SocialUser Login(String username, String password);
}
