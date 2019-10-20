package com.demo.springboot.repository;

import com.demo.springboot.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{


}
