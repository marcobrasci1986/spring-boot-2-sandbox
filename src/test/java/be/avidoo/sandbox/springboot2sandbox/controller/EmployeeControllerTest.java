package be.avidoo.sandbox.springboot2sandbox.controller;

import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import be.avidoo.sandbox.springboot2sandbox.service.EmployeeService;
import be.avidoo.sandbox.springboot2sandbox.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @WebMvcTest also auto-configures MockMvc. Mock MVC offers a powerful way to quickly test MVC controllers without needing to start a full HTTP server.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    public static final String EMPLOYEES_JSON = "employees.json";
    public static final String EMPLOYEE_JSON = "employee.json";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private EmployeeService employeeService;


    @Test
    public void TEST_HELLO_WORLD() throws Exception {
        this.mvc.perform(
                get("/api/hello-world").accept(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isOk()).andExpect(content().string("hello world"));
    }

    @Test
    public void TEST_FIND_ALL() throws Exception {
        String expectedJson = TestUtils.readFileAsString(this.getClass(), EMPLOYEES_JSON);

        given(this.employeeService.findAll()).willReturn(createEmployeeList());

        this.mvc.perform(get("/api/employees").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }

    @Test
    public void TEST_FIND_BY_FIRST_NAME() throws Exception {
        String expectedJson = TestUtils.readFileAsString(this.getClass(), EMPLOYEE_JSON);

        given(this.employeeService.findByFirstName("John")).willReturn(createEmployeeListSingleEntry());

        this.mvc.perform(get("/api/employee/{firstName}", "John").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }

    private List<Employee> createEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Employee.builder().firstName("John").lastName("Doe").build());
        employeeList.add(Employee.builder().firstName("Lebron").lastName("James").build());

        return employeeList;
    }

    private List<Employee> createEmployeeListSingleEntry() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Employee.builder().firstName("John").lastName("Doe").build());

        return employeeList;
    }


}