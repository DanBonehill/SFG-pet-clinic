package com.danbonehill.sfgpetclinic.services.springdatajpa;

import com.danbonehill.sfgpetclinic.model.Speciality;
import com.danbonehill.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDService service;

    private Speciality returnedSpecialty;
    private Long specialityId = 1L;

    @BeforeEach
    void setUp() {
        returnedSpecialty = Speciality.builder().id(specialityId).build();
    }

    @Test
    void findAll() {
        Set<Speciality> returnedSpecialities = new HashSet<>();
        returnedSpecialities.add(Speciality.builder().id(1L).build());
        returnedSpecialities.add(Speciality.builder().id(2L).build());

        when(specialtyRepository.findAll()).thenReturn(returnedSpecialities);

        Set<Speciality> specialities = service.findAll();

        assertNotNull(specialities);
        assertEquals(2, specialities.size());
    }

    @Test
    void findById() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(returnedSpecialty));

        Speciality speciality = service.findById(specialityId);

        assertNotNull(speciality);
    }

    @Test
    void save() {
        Speciality specialityToSave = Speciality.builder().id(specialityId).build();
        when(specialtyRepository.save(any())).thenReturn(returnedSpecialty);

        Speciality speciality = service.save(specialityToSave);

        assertNotNull(speciality);
    }

    @Test
    void delete() {
        service.delete(returnedSpecialty);

        assertEquals(new HashSet<>(), service.findAll());
        verify(specialtyRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(specialityId);

        assertEquals(new HashSet<>(), service.findAll());
        verify(specialtyRepository, times(1)).deleteById(anyLong());
    }
}