package com.demo.springboot.dto;

import com.demo.springboot.domain.Department;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class DepartmentResource extends Resource {

	private List<Department> departments;
	
	public DepartmentResource(List<Department> departments) {		
		this.departments = departments;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	
}
