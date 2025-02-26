package com.ipi.jva350.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class SalarieAideADomicileTest {

    @Test
    void testALegalementDroitADesCongesPayesDefaut() {
        // Given
        SalarieAideADomicile s = new SalarieAideADomicile();
        // When
        boolean result = s.aLegalementDroitADesCongesPayes();
        // Then
        assertFalse(result);
    }

    @Test
    void testALegalementDroitADesCongesPayesTrue() {
        // Given
        SalarieAideADomicile s = new SalarieAideADomicile();
        s.setJoursTravaillesAnneeNMoins1(100);
        // When
        boolean a = s.aLegalementDroitADesCongesPayes();
        // Then
        assertTrue(a);
    }

    @Test
    void testALegalementDroitADesCongesPayesFalse() {
        // Given
        SalarieAideADomicile s = new SalarieAideADomicile();
        s.setJoursTravaillesAnneeNMoins1(2);
        // When
        boolean a = s.aLegalementDroitADesCongesPayes();
        // Then
        assertFalse(a);
    }

    @Test
    void testALegalementDroitADesCongesPayes10jours() {
        // Given
        SalarieAideADomicile s = new SalarieAideADomicile();
        s.setJoursTravaillesAnneeNMoins1(10);
        // When
        boolean a = s.aLegalementDroitADesCongesPayes();
        // Then
        assertTrue(a);
    }

}
