package be.avidoo.sandbox.springboot2sandbox.criteria;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@EqualsAndHashCode
public class EmployeeSearchCriteria {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
