package com.demo.springboot.v1.controller;


import com.demo.springboot.domain.Department;
import com.demo.springboot.exception.ResourceNotFoundException;
import com.demo.springboot.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

;

@RestController("DepartmentControllerV1")
@RequestMapping("/v1")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@GetMapping("/departments")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	public ResponseEntity<Resources<Resource<Department>>> findAllDepartment(){
		List<Resource<Department>> departments = StreamSupport.stream(departmentService.findAll().spliterator(), false)
				.map(department -> new Resource<>(department,
					linkTo(methodOn(DepartmentController.class).findDepartment(department.getId())).withSelfRel(),
					linkTo(methodOn(DepartmentController.class).findAllDepartment()).withRel("employees")))
				.collect(Collectors.toList());

			return ResponseEntity.ok(
				new Resources<>(departments,
					linkTo(methodOn(DepartmentController.class).findAllDepartment()).withSelfRel()));
	}	

	
	@GetMapping("/departments/{id}")
	public ResponseEntity<Resource<Department>> findDepartment(@PathVariable Long id){
		this.validate(id);
		Optional<Department> result = departmentService.findById(id);
		Department department = result.get();
		Resource<Department> resource = new Resource<Department>(department, linkTo(methodOn(DepartmentController.class).findDepartment(department.getId())).withSelfRel(),
				linkTo(methodOn(DepartmentController.class).findAllDepartment()).withRel("departments")   
				);
		return new ResponseEntity<Resource<Department>>(resource, HttpStatus.OK);		
	}
	
	@ApiOperation(value="Create a new department")
	@ApiResponse(code= 201, message="successfully created department")
	@PostMapping(value="/departments", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createDepartment(@Valid @RequestBody Department department){
		 department = departmentService.save(department);
		 HttpHeaders responseHeaders = new HttpHeaders();
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(department.getId()).toUri();
		 responseHeaders.setLocation(uri);
		 return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/departments/{id}")
	public ResponseEntity<Void> updateDepartment(@Valid @RequestBody Department department){
		validate(department.getId());
		departmentService.save(department);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PatchMapping("/departments/{id}")
	public ResponseEntity<Void> patchDepartment(@RequestBody Department department){
		validate(department.getId());
		departmentService.save(department);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/departments/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
		validate(id);
		departmentService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	private void validate(Long id){
		departmentService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

}
