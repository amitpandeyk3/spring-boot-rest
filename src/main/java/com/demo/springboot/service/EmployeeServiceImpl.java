package com.demo.springboot.service;

import com.demo.springboot.domain.Department;
import com.demo.springboot.domain.Employee;
import com.demo.springboot.exception.ResourceNotFoundException;
import com.demo.springboot.repository.DepartmentRepository;
import com.demo.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    public EmployeeRepository employeeRepository;


    @Override
    public Employee save(Employee employee) {
        Department department = departmentRepository.findById(employee.getDepartment().getId()).orElseThrow(() -> new ResourceNotFoundException());
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

}
