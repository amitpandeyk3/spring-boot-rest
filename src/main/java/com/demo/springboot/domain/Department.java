package com.demo.springboot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="DEP", schema="HR")
public class Department {

    @Id
    @GeneratedValue
    @Column(name="DEPARTMENT_ID")
	private Long id;
    
    @NotEmpty
    @Column(name="DEPARTMENT_NAME")
	private String name;
    
    @OneToMany(mappedBy="department")
    private List<Employee> employees;

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
