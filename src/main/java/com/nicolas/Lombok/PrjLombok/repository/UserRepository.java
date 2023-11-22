package com.nicolas.Lombok.PrjLombok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicolas.Lombok.PrjLombok.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}