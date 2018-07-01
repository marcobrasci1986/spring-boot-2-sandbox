package be.avidoo.sandbox.springboot2sandbox.commands;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class EmployeeCommand {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Long departmentId;
}
