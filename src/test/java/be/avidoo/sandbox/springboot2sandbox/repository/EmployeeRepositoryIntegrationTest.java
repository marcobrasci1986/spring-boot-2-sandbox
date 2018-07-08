package be.avidoo.sandbox.springboot2sandbox.repository;

import be.avidoo.sandbox.springboot2sandbox.criteria.EmployeeSearchCriteria;
import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Sql({"/drop-schema.sql", "/employee-it.sql"})
@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

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

    @Test
    public void TEST_FIND_BY_CRITERIA_FIRST_NAME_AND_LAST_NAME() {
        EmployeeSearchCriteria criteria = EmployeeSearchCriteria.builder().firstName("Eden").lastName("Hazard").build();
        List<Employee> allEmployees = employeeRepository.findByCriteria(criteria);

        assertThat(allEmployees.size(), is(1));
        assertThat(allEmployees.get(0).getLastName(), is("Hazard"));
    }

    @Test
    public void TEST_FIND_BY_DATE_RANGE() {
        EmployeeSearchCriteria criteria = EmployeeSearchCriteria.builder()
                .birthDateFrom(LocalDate.of(1986, Month.JANUARY, 1))
                .birthDateTo(LocalDate.of(1990, Month.JANUARY, 1))
                .build();
        List<Employee> allEmployees = employeeRepository.findByCriteria(criteria);

        assertThat(allEmployees.size(), is(1));
        assertThat(allEmployees.get(0).getLastName(), is("Doe"));
    }
}