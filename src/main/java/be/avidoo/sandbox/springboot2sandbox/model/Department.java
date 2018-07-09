package be.avidoo.sandbox.springboot2sandbox.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT", schema = "demo")
@Getter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Department {

    @Id
    @GeneratedValue(generator = "department_id_seq")
    @SequenceGenerator(name = "department_id_seq", sequenceName = "department_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;
    
}
