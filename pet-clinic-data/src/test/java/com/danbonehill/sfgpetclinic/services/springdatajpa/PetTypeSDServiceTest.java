package com.danbonehill.sfgpetclinic.services.springdatajpa;

import com.danbonehill.sfgpetclinic.model.PetType;
import com.danbonehill.sfgpetclinic.repositories.PetTypeRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetTypeSDServiceTest {
    
    @Mock
    PetTypeRepository petTypeRepository;
    
    @InjectMocks
    PetTypeSDService service;
    
    private PetType returnedPetType;
    private Long petTypeId = 1L;

    @BeforeEach
    void setUp() {
        returnedPetType = PetType.builder().id(petTypeId).build();
    }

    @Test
    void findAll() {
        Set<PetType> returnedPetTypes = new HashSet<>();
        returnedPetTypes.add(PetType.builder().id(1L).build());
        returnedPetTypes.add(PetType.builder().id(2L).build());

        when(petTypeRepository.findAll()).thenReturn(returnedPetTypes);

        Set<PetType> petTypes = service.findAll();

        assertNotNull(petTypes);
        assertEquals(2, petTypes.size());
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnedPetType));

        PetType petType = service.findById(petTypeId);

        assertNotNull(petType);
    }

    @Test
    void findByIdNotFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        PetType petType = service.findById(petTypeId);

        assertNull(petType);
    }

    @Test
    void save() {
        PetType petTypeToSave = PetType.builder().id(petTypeId).build();

        when(petTypeRepository.save(any())).thenReturn(returnedPetType);

        PetType petType = service.save(petTypeToSave);

        assertNotNull(petType);
    }

    @Test
    void delete() {
        service.delete(returnedPetType);

        assertEquals(new HashSet<>(), service.findAll());
        verify(petTypeRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(petTypeId);

        assertEquals(new HashSet<>(), service.findAll());
        verify(petTypeRepository, times(1)).deleteById(anyLong());
    }
}