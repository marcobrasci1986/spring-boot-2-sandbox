package be.avidoo.sandbox.springboot2sandbox.repository.impl;

import be.avidoo.sandbox.springboot2sandbox.criteria.EmployeeSearchCriteria;
import be.avidoo.sandbox.springboot2sandbox.model.Employee;
import be.avidoo.sandbox.springboot2sandbox.model.QEmployee;
import be.avidoo.sandbox.springboot2sandbox.repository.EmployeeRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<Employee> findByCriteria(EmployeeSearchCriteria searchCriteria) {
        JPAQuery<?> jpaQuery = new JPAQuery<Void>(entityManager);
        QEmployee employee = QEmployee.employee;

        JPAQuery<?> query = jpaQuery.from(employee);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (searchCriteria.getFirstName() != null) {
            booleanBuilder.and(employee.firstName.eq(searchCriteria.getFirstName()));
        }

        if (searchCriteria.getFirstName() != null) {
            booleanBuilder.and(employee.lastName.eq(searchCriteria.getLastName()));
        }

        return query
                .select(employee)
                .from(employee)
                .where(booleanBuilder)
                .fetch();
    }
}
