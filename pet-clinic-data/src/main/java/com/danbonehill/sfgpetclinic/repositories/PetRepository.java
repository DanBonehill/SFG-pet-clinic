package com.danbonehill.sfgpetclinic.repositories;

import com.danbonehill.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
