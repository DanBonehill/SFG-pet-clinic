package com.danbonehill.sfgpetclinic.services;

import com.danbonehill.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
