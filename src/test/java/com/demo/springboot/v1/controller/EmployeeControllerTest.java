package com.demo.springboot.v1.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.springboot.domain.Employee;
import com.demo.springboot.fixture.EmployeeFixture;
import com.demo.springboot.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@Test
	public void findEmployee_200() throws Exception {
		Optional<Employee> employee = Optional.of(EmployeeFixture.createEmployee(1L,"Sue"));
		when(employeeRepository.findById(anyLong())).thenReturn(employee);				
		this.mockMvc.perform(get("/v1/employees/{id}", 1L))
		             .andExpect(status().isOk())
		             .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		             .andExpect(jsonPath("$.id", is(equalTo(1))))
		             .andExpect(jsonPath("$.name", is("Sue")));
		verify(employeeRepository, times(2)).findById(anyLong());
	}
	
	@Test
	public void findEmployee_returns_404() throws Exception {
		Optional<Employee> employee = Optional.empty();
		when(employeeRepository.findById(anyLong())).thenReturn(employee);
		this.mockMvc.perform(get("/v1/employees/{id}", 1L))
		                  .andExpect(status().isNotFound());		                  
	}
	
	@Test
	public void createEmployee_returns_201() throws Exception {
		Employee employee = EmployeeFixture.createEmployee(1L,"Sue");
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		this.mockMvc.perform(post("/v1/employees").content(asJsonString(employee))
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isCreated())
		        .andExpect(header().string("location", containsString("/employees/1")));
		
	}
		
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
