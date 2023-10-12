package io.github.zemise.security01.domain.onetoone_unidirectional;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByName(String name);
}
