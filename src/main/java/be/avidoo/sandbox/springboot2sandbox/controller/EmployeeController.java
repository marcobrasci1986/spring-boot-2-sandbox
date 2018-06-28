package be.avidoo.sandbox.springboot2sandbox.controller;

import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import be.avidoo.sandbox.springboot2sandbox.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController extends BaseController{

    private final EmployeeService employeeService;


    @GetMapping(path = "/employees")
    public List<Employee> findAll() {
        return this.employeeService.findAll();
    }
}
