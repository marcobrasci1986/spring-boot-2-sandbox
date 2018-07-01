package be.avidoo.sandbox.springboot2sandbox.mappers;

import be.avidoo.sandbox.springboot2sandbox.commands.EmployeeCommand;
import be.avidoo.sandbox.springboot2sandbox.model.Department;
import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import be.avidoo.sandbox.springboot2sandbox.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class EmployeeMapper {

    private final DepartmentService departmentService;

    public Employee toEntity(EmployeeCommand command) {
        if (command == null) {
            return null;
        }
        Optional<Department> department = departmentService.findById(command.getDepartmentId());

        return Employee.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .birthDate(command.getBirthDate())
                .department(department.orElse(null))
                .build();
    }

    public EmployeeCommand toCommand(Employee entity) {
        if (entity == null) {
            return null;
        }
        return EmployeeCommand.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .departmentId(entity.getDepartment().getId())
                .build();
    }
}
