package com.demo.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.springboot.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
