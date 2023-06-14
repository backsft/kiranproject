package com.KiranProject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KiranProject.EntityArea.Employee;

public interface EmpRepo extends JpaRepository<Employee, Long>{
	
	

}
