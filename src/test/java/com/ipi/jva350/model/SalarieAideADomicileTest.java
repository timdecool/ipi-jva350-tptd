package com.ipi.jva350.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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

    @ParameterizedTest()
    @CsvSource({
            "'2025-02-26','2025-03-01','[2025-02-26, 2025-02-27, 2025-02-28, 2025-03-01]'"
    })
    void testCalculJourDeCongesDecomptesPourPlage(String dateDebut, String dateFin, String expected) {
        SalarieAideADomicile s = new SalarieAideADomicile();
        Set<LocalDate> actualCongesDecomptes = s.calculeJoursDeCongeDecomptesPourPlage(LocalDate.parse(dateDebut), LocalDate.parse(dateFin));

        System.out.println(actualCongesDecomptes.toString());
        System.out.println(expected);

        assertEquals(expected, actualCongesDecomptes.toString());
    }


}
