package com.demo.springboot.fixture;

import com.demo.springboot.domain.Department;

public class DepartmentFixture {
	
	public static Department createDepartment(Long id, String name) {
		Department department = new Department();
		department.setId(id);
		department.setName(name);
		return department;
	}

}
