package com.danbonehill.sfgpetclinic.services.springdatajpa;

import com.danbonehill.sfgpetclinic.model.Visit;
import com.danbonehill.sfgpetclinic.repositories.VisitRepository;
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
class VisitSDServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDService service;

    private Visit returnedVisit;
    private Long visitId = 1L;

    @BeforeEach
    void setUp() {
        returnedVisit = Visit.builder().id(visitId).build();
    }

    @Test
    void findAll() {
        Set<Visit> returnedVisits = new HashSet<>();
        returnedVisits.add(Visit.builder().id(1L).build());
        returnedVisits.add(Visit.builder().id(2L).build());

        when(visitRepository.findAll()).thenReturn(returnedVisits);

        Set<Visit> visits = service.findAll();

        assertNotNull(visits);
        assertEquals(2, visits.size());
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnedVisit));

        Visit visit = service.findById(visitId);

        assertNotNull(visit);
    }

    @Test
    void findByIdNotFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());

        Visit visit = service.findById(visitId);

        assertNull(visit);
    }

    @Test
    void save() {
        Visit visitToSave = Visit.builder().id(visitId).build();
        when(visitRepository.save(any())).thenReturn(returnedVisit);

        Visit visit = service.save(visitToSave);

        assertNotNull(visit);
    }

    @Test
    void delete() {
        service.delete(returnedVisit);

        assertEquals(new HashSet<>(), service.findAll());
        verify(visitRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(visitId);

        assertEquals(new HashSet<>(), service.findAll());
        verify(visitRepository, times(1)).deleteById(anyLong());
    }
}