package be.avidoo.sandbox.springboot2sandbox.controller;

import be.avidoo.sandbox.springboot2sandbox.exceptions.NotFoundException;
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

import static be.avidoo.sandbox.springboot2sandbox.controller.aspect.AbstractAdvice.EXCEPTION_CODE_HEADER_NAME;
import static be.avidoo.sandbox.springboot2sandbox.controller.aspect.AbstractAdvice.EXCEPTION_MESSAGE_HEADER_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @WebMvcTest also auto-configures MockMvc. Mock MVC offers a powerful way to quickly test MVC controllers without needing to start a full HTTP server.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    public static final String EMPLOYEES_JSON = "employees.json";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;


    @Test
    public void TEST_HELLO_WORLD() throws Exception {
        this.mockMvc.perform(
                get("/api/hello-world").accept(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isOk()).andExpect(content().string("hello world"));
    }

    @Test
    public void TEST_FIND_ALL() throws Exception {
        String expectedJson = TestUtils.readFileAsString(this.getClass(), EMPLOYEES_JSON);

        given(this.employeeService.findAll()).willReturn(createEmployeeList());

        this.mockMvc.perform(get("/api/employees").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }


    @Test
    public void TEST_FIND_BY_ID() throws Exception {
        String errorMessage = "Some error message";
        given(this.employeeService.findById(888L)).willThrow(new NotFoundException(errorMessage));

        this.mockMvc.perform(get("/api/employee/{id}", 888l).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(header().string(EXCEPTION_CODE_HEADER_NAME, equalTo(NotFoundException.ERROR_CODE)))
                .andExpect(header().string(EXCEPTION_MESSAGE_HEADER_NAME, equalTo(errorMessage)));
    }

    private List<Employee> createEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Employee.builder().firstName("John").lastName("Doe").build());
        employeeList.add(Employee.builder().firstName("Lebron").lastName("James").build());

        return employeeList;
    }

}