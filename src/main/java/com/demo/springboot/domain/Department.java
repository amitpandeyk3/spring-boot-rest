package com.demo.springboot.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="DEP")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Department  {

    @Id
    @GeneratedValue
    @Column(name="DEPARTMENT_ID")
    @ApiModelProperty(notes = "The database generated department ID")
	private Long id;
    
    @NotEmpty
    @Column(name="DEPARTMENT_NAME", nullable = false)
	private String name;
    
    @OneToMany(mappedBy="department")
    private List<Employee> employees;

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
}
