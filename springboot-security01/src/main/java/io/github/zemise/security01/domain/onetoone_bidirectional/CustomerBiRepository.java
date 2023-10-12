package io.github.zemise.security01.domain.onetoone_bidirectional;

import org.springframework.data.repository.CrudRepository;

public interface CustomerBiRepository extends CrudRepository<BiCustomer, Long> {
    BiCustomer findByName(String name);
}
