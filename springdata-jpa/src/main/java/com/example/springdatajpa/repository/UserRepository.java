package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Dao层
 * @author Baozi
 */
public interface UserRepository extends JpaRepository<User, String> {
    }

