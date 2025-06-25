package com.example.jwt_demo1;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	Employee findByNameAndPassword(String name, String password);


	
}
