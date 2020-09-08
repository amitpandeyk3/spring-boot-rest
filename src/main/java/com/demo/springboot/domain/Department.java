package com.demo.springboot.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="DEP")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Department {

	@Id
	@SequenceGenerator(name = "Dep_Gen", sequenceName = "Dep_seq")
	@GeneratedValue(generator = "Dep_Gen")
	@Column(name = "DEPARTMENT_ID")
	@ApiModelProperty(notes = "The database generated department ID")
	private Long id;

	@NotEmpty
	@Column(name = "DEPARTMENT_NAME", nullable = false)
	private String name;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Employee> employees;

	@UpdateTimestamp
	@Column(name = "LAST_UPDATED_DATE")
	private LocalDateTime lastUpdatedDate;

	@Column(name = "CREATED_DATE", updatable = false)
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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

	public void addEmployee(Employee employee) {
		this.employees.add(employee);
		employee.setDepartment(this);
	}

	public void removeEmployee(Employee employee) {
		employee.setDepartment(null);
		this.employees.remove(employee);
	}

	public void removeEmployees() {
		Iterator<Employee> iterator = this.employees.iterator();
		while (iterator.hasNext()) {
			Employee employee = iterator.next();
			employee.setDepartment(null);
			iterator.remove();
		}
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

		Department department = (Department) object;
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