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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;
    
}
