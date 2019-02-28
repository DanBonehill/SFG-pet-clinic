package com.danbonehill.sfgpetclinic.repositories;

import com.danbonehill.sfgpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
