package io.github.zemise.security01.demo.manytomany;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Employee findByName(String name);
}
