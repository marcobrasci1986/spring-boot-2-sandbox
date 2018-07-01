package be.avidoo.sandbox.springboot2sandbox.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty(value = "This is the firstName of the employee", required = true)
    private String firstName;
    @ApiModelProperty(value = "This is the lastName of the employee", required = true)
    private String lastName;
    private LocalDate birthDate;

    @ManyToOne
    private Department department;
}
