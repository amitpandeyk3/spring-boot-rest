package com.demo.springboot.v2.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.domain.Department;
import com.demo.springboot.dto.DepartmentResource;
import com.demo.springboot.dto.Meta;
import com.demo.springboot.repository.DepartmentRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("DepartmentControllerV2")
@RequestMapping("/v2")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	
	//Another way to create version is to use custom MIME type like produces="application/vnd.payroll.v2+json"
	@GetMapping(value="/departments")
	@ApiOperation(value="Get list of departments with pagination support")
	public DepartmentResource findAllDepartment_byPageSize(@ApiParam(value = "Page index", required = true) @RequestParam int page,
			@ApiParam(value = "Page size", required = true) @RequestParam int size){	
		Pageable pageable = PageRequest.of(page, size);
		Page<Department> pageableDepartments  = departmentRepository.findAll(pageable);		
		DepartmentResource departmentResource = new DepartmentResource(pageableDepartments.getContent());
		Meta meta = new Meta.Builder(pageableDepartments.getPageable().getOffset(), pageableDepartments.getPageable().getPageSize())
				 .totalElements(pageableDepartments.getTotalElements())
				 .pageNumber(pageableDepartments.getPageable().getPageNumber())
				 .totalPages(pageableDepartments.getTotalPages())
				 .last(pageableDepartments.isLast()).build();
		departmentResource.setMeta(meta);
		return departmentResource;		
	}
	
}
