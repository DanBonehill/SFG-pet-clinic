package com.danbonehill.sfgpetclinic.services.springdatajpa;

import com.danbonehill.sfgpetclinic.model.Vet;
import com.danbonehill.sfgpetclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetsSDServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetsSDService service;

    Vet returnedVet;

    @BeforeEach
    void setUp() {
        returnedVet = Vet.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Vet> returnedVets = new HashSet<>();
        returnedVets.add(Vet.builder().id(1L).build());
        returnedVets.add(Vet.builder().id(2L).build());

        when(vetRepository.findAll()).thenReturn(returnedVets);

        Set<Vet> vets = service.findAll();

        assertNotNull(vets);
        assertEquals(2, vets.size());
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnedVet));

        Vet vet = service.findById(1L);

        assertNotNull(vet);
    }

    @Test
    void findByIdNotFound() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());

        Vet vet = service.findById(1L);

        assertNull(vet);
    }

    @Test
    void save() {
        Vet vetToSave = Vet.builder().id(1L).build();
        when(vetRepository.save(any())).thenReturn(returnedVet);

        Vet vet = service.save(vetToSave);

        assertNotNull(vet);
    }

    @Test
    void delete() {
        service.delete(returnedVet);

        assertEquals(new HashSet<>(), service.findAll());
        verify(vetRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        assertEquals(new HashSet<>(), service.findAll());
        verify(vetRepository, times(1)).deleteById(anyLong());
    }
}