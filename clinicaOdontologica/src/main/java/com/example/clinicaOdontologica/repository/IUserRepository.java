package com.example.clinicaOdontologica.repository;

import com.example.clinicaOdontologica.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User,Long> {

    @Query("from User u where u.username =:username")
    Optional<User> findByUsername(@Param("username") String username);
}
