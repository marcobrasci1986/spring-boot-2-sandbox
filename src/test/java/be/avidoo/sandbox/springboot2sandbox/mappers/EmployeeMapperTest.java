package be.avidoo.sandbox.springboot2sandbox.mappers;

import be.avidoo.sandbox.springboot2sandbox.commands.EmployeeCommand;
import be.avidoo.sandbox.springboot2sandbox.model.Department;
import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import be.avidoo.sandbox.springboot2sandbox.service.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeMapperTest {

    private EmployeeMapper employeeMapper;
    @Mock
    private DepartmentService departmentService;

    @Before
    public void setUp() throws Exception {
        employeeMapper = new EmployeeMapper(departmentService);

        when(departmentService.findById(1L)).thenReturn(Optional.of(Department.builder().id(1l).departmentName("IT").build()));
        when(departmentService.findById(99L)).thenReturn(Optional.empty());
    }

    @Test
    public void TEST_MAP_NULL() {
        EmployeeCommand result = employeeMapper.toCommand(null);

        assertThat(result, is(nullValue()));
    }


    @Test
    public void TEST_MAP_DEPARTMENT_NULL() {
        EmployeeCommand employeeCommand = EmployeeCommand.builder()
                .firstName("John")
                .lastName("Doe")
                .birthDate(LocalDate.of(1986, Month.APRIL, 22))
                .departmentId(99l)
                .build();
        Employee result = employeeMapper.toEntity(employeeCommand);

        assertThat(result.getFirstName(), is(employeeCommand.getFirstName()));
        assertThat(result.getLastName(), is(employeeCommand.getLastName()));
        assertThat(result.getBirthDate(), is(employeeCommand.getBirthDate()));
        assertThat(result.getDepartment(), is(nullValue()));
    }

    @Test
    public void TEST_MAP_DEPARTMENT_NOT_NULL() {
        EmployeeCommand employeeCommand = EmployeeCommand.builder()
                .firstName("John")
                .lastName("Doe")
                .birthDate(LocalDate.of(1986, Month.APRIL, 22))
                .departmentId(1l)
                .build();
        Employee result = employeeMapper.toEntity(employeeCommand);

        assertThat(result.getFirstName(), is(employeeCommand.getFirstName()));
        assertThat(result.getLastName(), is(employeeCommand.getLastName()));
        assertThat(result.getBirthDate(), is(employeeCommand.getBirthDate()));
        assertThat(result.getDepartment(), is(notNullValue()));
        assertThat(result.getDepartment().getDepartmentName(), is("IT"));
    }
}