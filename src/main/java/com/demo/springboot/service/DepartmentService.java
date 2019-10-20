package com.demo.springboot.service;

import com.demo.springboot.domain.Department;
import com.demo.springboot.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Department save(Department employee);

    Optional<Department> findById(Long id);

    List<Department> findAll();

    void deleteById(Long Id);

}
