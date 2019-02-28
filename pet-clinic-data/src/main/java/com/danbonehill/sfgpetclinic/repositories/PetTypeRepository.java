package com.danbonehill.sfgpetclinic.repositories;

import com.danbonehill.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
