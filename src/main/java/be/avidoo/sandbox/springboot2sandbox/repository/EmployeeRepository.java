package be.avidoo.sandbox.springboot2sandbox.repository;

import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
