package be.avidoo.sandbox.springboot2sandbox.service;

import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import be.avidoo.sandbox.springboot2sandbox.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        log.debug("debug employee:: findAll");
        log.info("info employee:: findAll");
        log.warn("warn employee:: findAll");
        log.error("error employee:: findAll");
        return this.employeeRepository.findAll();
    }

}
