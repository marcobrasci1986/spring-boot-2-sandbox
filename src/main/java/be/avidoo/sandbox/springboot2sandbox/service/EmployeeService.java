package be.avidoo.sandbox.springboot2sandbox.service;

import be.avidoo.sandbox.springboot2sandbox.commands.EmployeeCommand;
import be.avidoo.sandbox.springboot2sandbox.mappers.EmployeeMapper;
import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import be.avidoo.sandbox.springboot2sandbox.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public List<Employee> findAll() {
        log.debug("debug employee:: findAll");
        log.info("info employee:: findAll");
        log.warn("warn employee:: findAll");
        log.error("error employee:: findAll");
        return this.employeeRepository.findAll();
    }

    public List<Employee> findByFirstName(String firstName) {
        return this.employeeRepository.findByFirstName(firstName);
    }

    @Transactional
    public EmployeeCommand saveEmployee(EmployeeCommand employeeCommand) {
        Employee detachedEmployee = employeeMapper.toEntity(employeeCommand);
        Employee savedEmployee = employeeRepository.save(detachedEmployee);
        return employeeMapper.toCommand(savedEmployee);
    }

}
