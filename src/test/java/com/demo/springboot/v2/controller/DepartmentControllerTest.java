package com.demo.springboot.v2.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.springboot.domain.Department;
import com.demo.springboot.fixture.DepartmentFixture;
import com.demo.springboot.repository.DepartmentRepository;


@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DepartmentRepository departmentRepository;
	

	@Test
	public void findAllDepartment_withPagination() throws Exception {
		List<Department> departments  = Arrays.asList(DepartmentFixture.createDepartment(1L,"hr"), DepartmentFixture.createDepartment(2L,"it"));
		Pageable pageable = PageRequest.of(0, 2);
		Page<Department> pageResult= new PageImpl<Department>(departments, pageable, 2);
		when(departmentRepository.findAll(any(Pageable.class))).thenReturn(pageResult);				
		this.mockMvc.perform(get("/v2/departments").param("page", "0")
				      .param("size", "2"))				   
		              .andExpect(jsonPath("$.content[0].id", is(equalTo(1))))
                      .andExpect(jsonPath("$.content[0].name", is("hr")))
                      .andExpect(jsonPath("$.content[1].id", is(equalTo(2))))
                      .andExpect(jsonPath("$.content[1].name", is("it")))
                      .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                      .andExpect(jsonPath("$.pageable.offset", is(0)))
                      .andExpect(jsonPath("$.pageable.pageSize", is(2)))
                      .andExpect(jsonPath("$.totalPages", is(1)))
                      .andExpect(jsonPath("$.totalElements", is(2)))
                      .andExpect(jsonPath("$.last", is(true)))
		              .andExpect(status().isOk())
		              .andDo(print());
		            
	}
	
	

}
