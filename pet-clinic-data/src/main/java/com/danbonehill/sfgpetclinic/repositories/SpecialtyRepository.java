package com.danbonehill.sfgpetclinic.repositories;

import com.danbonehill.sfgpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Speciality, Long> {
}
