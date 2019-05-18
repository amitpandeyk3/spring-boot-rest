package com.demo.springboot.controller;



import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import com.demo.springboot.domain.Department;
import com.demo.springboot.exception.ResourceNotFoundException;
import com.demo.springboot.repository.DepartmentRepository;

@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> findAllDepartment(){
		List<Department> departments  = departmentRepository.findAll();
		return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
	}
	
	@GetMapping(value="/departments", produces="application/vnd.payroll.v2+json")
	public ResponseEntity<Page<Department>> findAllDepartment(@RequestParam int page, @RequestParam int size){	
		Pageable pageable = PageRequest.of(page, size);
		Page<Department> departments  = departmentRepository.findAll(pageable);		
		return new ResponseEntity<>(departments, HttpStatus.OK);
		
	}
	
	@GetMapping("/departments/{id}")
	public ResponseEntity<?> findDepartment(@PathVariable Long id){
		this.validate(id);
		Optional<Department> department = departmentRepository.findById(id);
		return new ResponseEntity<Department>(department.get(), HttpStatus.OK);		
	}
	
	@PostMapping(value="/departments", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createDepartment(@Valid @RequestBody Department department){
		 department = departmentRepository.save(department);
		 HttpHeaders responseHeaders = new HttpHeaders();
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(department.getId()).toUri();
		 responseHeaders.setLocation(uri);
		 return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/departments/{id}")
	public ResponseEntity<Void> updateDepartment(@Valid @RequestBody Department department){
		validate(department.getId());
		departmentRepository.save(department);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PatchMapping("/departments/{id}")
	public ResponseEntity<Void> updatePartialDepartment(@RequestBody Department department){
		validate(department.getId());
		departmentRepository.save(department);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/departments/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
		validate(id);
		departmentRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	private void validate(Long id){
 	  departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

}
