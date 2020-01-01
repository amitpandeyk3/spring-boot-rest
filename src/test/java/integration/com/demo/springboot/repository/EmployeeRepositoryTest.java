package integration.com.demo.springboot.repository;

import com.demo.springboot.Application;
import com.demo.springboot.domain.Employee;
import com.demo.springboot.repository.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.*;

import java.util.Collection;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class EmployeeRepositoryTest {

 private static Logger logger = LoggerFactory.getLogger(EmployeeRepositoryTest.class);

 @Autowired
 EmployeeRepository employeeRepository;

 @Test
 @SqlGroup({
        @Sql(value = "classpath:db/test-data.sql",
                config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "classpath:db/clean-up.sql",
                config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
        })
  public void findAll(){
     List<Employee> employeeList = employeeRepository.findAll();
     Assert.assertThat(employeeList, notNullValue());
     Assert.assertThat(employeeList, Matchers.<Collection<Employee>> allOf(
             hasSize(greaterThan(1)) ));
 }

}
