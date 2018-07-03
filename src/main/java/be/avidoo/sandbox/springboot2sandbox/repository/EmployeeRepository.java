package be.avidoo.sandbox.springboot2sandbox.repository;

import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {

    List<Employee> findByFirstName(String firstName);

}
