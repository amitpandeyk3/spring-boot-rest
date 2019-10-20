package com.demo.springboot.service;

import com.demo.springboot.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee save(Employee employee);

    Optional<Employee> findById(Long id);

    List<Employee> findAll();

    void deleteById(Long Id);

}
