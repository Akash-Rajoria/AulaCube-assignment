package com.example.demo;

import java.sql.Timestamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.entities.Employee;
import com.example.demo.jparepository.JpaRepositoryTest;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//JpaRepositoryTest repo=context.getBean(JpaRepositoryTest.class);
		//Employee emp=new Employee("vikas","kumar","vikka.jjj@example.com","user","11.20","11.20");
		//repo.save(emp);
	}

}
