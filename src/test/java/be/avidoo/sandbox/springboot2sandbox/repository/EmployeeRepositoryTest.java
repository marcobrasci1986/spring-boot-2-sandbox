package be.avidoo.sandbox.springboot2sandbox.repository;

import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void TEST_FIND_ALL() {
        List<Employee> allEmployees = employeeRepository.findAll();
        assertThat(allEmployees.size(), is(3));
    }

    @Test
    public void TEST_FIND_BY_FIRST_NAME() {
        List<Employee> allEmployees = employeeRepository.findByFirstName("Eden");
        assertThat(allEmployees.size(), is(1));
        assertThat(allEmployees.get(0).getLastName(), is("Hazard"));
    }
}