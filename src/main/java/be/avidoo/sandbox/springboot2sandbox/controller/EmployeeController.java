package be.avidoo.sandbox.springboot2sandbox.controller;

import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import be.avidoo.sandbox.springboot2sandbox.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "The Employee controller custom description")
@RestController
@RequiredArgsConstructor
public class EmployeeController extends BaseController {

    private final EmployeeService employeeService;

    @GetMapping(path = "/hello-world", produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloWorld() {
        return "hello world";
    }


    @ApiOperation(value = "Get the list of employees", notes = "These are the special notes of this method")
    @GetMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> findAll() {
        return this.employeeService.findAll();
    }

    @ApiOperation(value = "Find an employee by its firstName")
    @GetMapping(path = "/employee/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> findByFirstName(@PathVariable String firstName) {
        return this.employeeService.findByFirstName(firstName);
    }
}
