package com.example.demo.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Employee;

public interface JpaRepositoryTest extends JpaRepository<Employee , Long>{

}
