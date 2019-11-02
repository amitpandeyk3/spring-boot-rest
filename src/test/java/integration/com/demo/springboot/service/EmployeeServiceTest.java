package integration.com.demo.springboot.service;

import com.demo.springboot.Application;
import com.demo.springboot.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = {Application.class})
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void findAll(){

    }
}
