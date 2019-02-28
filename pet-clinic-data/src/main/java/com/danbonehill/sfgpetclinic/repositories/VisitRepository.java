package com.danbonehill.sfgpetclinic.repositories;

import com.danbonehill.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
