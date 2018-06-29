package be.avidoo.sandbox.springboot2sandbox.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class EmployeeTest {

    public static final Employee EMPLOYEE = Employee.builder()
            .id(1L)
            .firstName("John")
            .lastName("Doe")
            .birthDate(LocalDate.of(1986, Month.APRIL, 22))
            .department(Department.builder()
                    .id(1L)
                    .departmentName("IT")
                    .build())
            .build();
    @Autowired
    private JacksonTester<Employee> jacksonTester;

    @Test
    public void TEST_SERIALIZE() throws IOException {
        assertThat(this.jacksonTester.write(EMPLOYEE)).isEqualToJson("employee.json", JSONCompareMode.STRICT);
    }

    @Test
    public void TEST_DESERIALIZE() throws IOException {
        final Employee employee = jacksonTester.read("employee.json").getObject();

        assertThat(EMPLOYEE).isEqualTo(employee);
    }
}