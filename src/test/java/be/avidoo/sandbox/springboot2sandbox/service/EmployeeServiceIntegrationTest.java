package be.avidoo.sandbox.springboot2sandbox.service;

import be.avidoo.sandbox.springboot2sandbox.commands.EmployeeCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void TEST_SAVE_EMPLOYEE() {
        EmployeeCommand employeeCommand = EmployeeCommand.builder()
                .firstName("Jan")
                .lastName("Vertongen")
                .birthDate(LocalDate.of(1986, Month.APRIL, 22))
                .departmentId(1001L)
                .build();

        EmployeeCommand savedEmployee = employeeService.saveEmployee(employeeCommand);

        assertThat(savedEmployee.getFirstName(), is(employeeCommand.getFirstName()));
        assertThat(savedEmployee.getLastName(), is(employeeCommand.getLastName()));
        assertThat(savedEmployee.getBirthDate(), is(employeeCommand.getBirthDate()));
        assertThat(savedEmployee.getDepartmentId(), is(employeeCommand.getDepartmentId()));
    }
}