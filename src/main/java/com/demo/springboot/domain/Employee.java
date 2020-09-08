package com.demo.springboot.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="EMP")
@JsonInclude(Include.NON_NULL)
public class Employee  {

	@Id
	@SequenceGenerator(name="Emp_Gen", sequenceName = "Emp_Seq")
	@GeneratedValue(generator="Emp_Gen")
	@Column(name="EMPLOYEE_ID")
	private Long id;
	
	@Column(name="EMPLOYEE_NAME")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="DEPARTMENT_ID")
	private Department department;

	@UpdateTimestamp
	@Column(name="LAST_UPDATED_DATE")
	private LocalDateTime lastUpdatedDate;

	@Column(name="CREATED_DATE", updatable=false)
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

	@Override
	public boolean equals(Object object){
		if(this == object){
			return true;
		}
		if(object == null){
			return false;
		}
		if(getClass() != object.getClass()){
			return false;
		}

		Employee department = (Employee) object;
		return id!=null && id.equals(department.id);
	}

	@Override
	public int hashCode(){
		return Objects.hashCode(this.id);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).
				append("id", id).
				append("name", name).
				toString();
	}
}
