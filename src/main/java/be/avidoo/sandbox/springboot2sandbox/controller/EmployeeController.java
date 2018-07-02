package be.avidoo.sandbox.springboot2sandbox.controller;

import be.avidoo.sandbox.springboot2sandbox.commands.EmployeeCommand;
import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import be.avidoo.sandbox.springboot2sandbox.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    /// TODO /search with criteria

//    @ApiOperation(value = "Find an employee by its firstName")
//    @GetMapping(path = "/employee/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Employee> findByFirstName(@PathVariable String firstName) {
//        return this.employeeService.findByFirstName(firstName);
//    }

    @ApiOperation(value = "Find an employee by its id")
    @GetMapping(path = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee findById(@PathVariable Long id) {
        return this.employeeService.findById(id);
    }

    /**
     * The @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.
     * The @RequestBody annotation maps the HttpRequest body to a pojo object, enabling automatic deserialization of the inbound HttpRequest body onto a Java object.
     */
    @ApiOperation(value = "Adds a new Employee")
    @PostMapping(path = "/employee/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EmployeeCommand save(@RequestBody EmployeeCommand employeeCommand) {
        return this.employeeService.saveEmployee(employeeCommand);
    }

    @ApiOperation(value = "Deletes an Employee")
    @DeleteMapping(path = "/employee/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        this.employeeService.delete(id);
    }
}
