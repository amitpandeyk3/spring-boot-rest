package com.demo.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="EMP")
@JsonInclude(Include.NON_NULL)
public class Employee  {

	@Id
	@GeneratedValue
	@Column(name="EMPLOYEE_ID")
	private Long id;
	
	@Column(name="EMPLOYEE_NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	private Department department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
