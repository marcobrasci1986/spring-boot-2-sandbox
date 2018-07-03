package be.avidoo.sandbox.springboot2sandbox.repository;

import be.avidoo.sandbox.springboot2sandbox.criteria.EmployeeSearchCriteria;
import be.avidoo.sandbox.springboot2sandbox.model.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {

    List<Employee> findByCriteria(EmployeeSearchCriteria searchCriteria);
}
