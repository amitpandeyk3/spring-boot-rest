package com.demo.springboot.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@Column(name = "CreatedDate", updatable=false)
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Version
	private Integer version;

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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}


	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
