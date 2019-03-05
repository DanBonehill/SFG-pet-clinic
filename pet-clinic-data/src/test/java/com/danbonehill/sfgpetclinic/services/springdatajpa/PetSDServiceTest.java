package com.danbonehill.sfgpetclinic.services.springdatajpa;

import com.danbonehill.sfgpetclinic.model.Pet;
import com.danbonehill.sfgpetclinic.repositories.PetRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetSDServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDService service;

    private Pet returnedPet;
    private Long petId = 1L;

    @BeforeEach
    void setUp() {
        returnedPet = Pet.builder().id(petId).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnedPets = new HashSet<>();
        returnedPets.add(Pet.builder().id(1L).build());
        returnedPets.add(Pet.builder().id(2L).build());

        when(petRepository.findAll()).thenReturn(returnedPets);

        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnedPet));

        Pet pet = service.findById(petId);

        assertNotNull(pet);
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());

        Pet pet = service.findById(petId);

        assertNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().id(petId).build();
        when(petRepository.save(any())).thenReturn(returnedPet);

        Pet pet = service.save(petToSave);

        assertNotNull(pet);
    }

    @Test
    void delete() {
        service.delete(returnedPet);

        assertEquals(new HashSet<>(), service.findAll());
        verify(petRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(petId);

        assertEquals(new HashSet<>(), service.findAll());
        verify(petRepository, times(1)).deleteById(anyLong());
    }
}