package com.demo.springboot.fixture;

import com.demo.springboot.domain.Department;
import com.demo.springboot.domain.Employee;

public class EmployeeFixture {

	
	public static Employee createEmployee(Long id, String name) {
		Employee employee = new Employee();
		employee.setId(id);
		employee.setName(name);
		return employee;
	}
}
