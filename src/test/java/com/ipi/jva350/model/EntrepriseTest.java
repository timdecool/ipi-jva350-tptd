package com.ipi.jva350.model;

import com.ipi.jva350.exception.WrongDateException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Le TDD permet de coder la feature directement avec en tête les cas aux bornes
 * ainsi que de penser aux levées d'exeptions qui puevtn rapidement passer aux travers des mailles du filets en bdd tant que le cas problématique n'est pas apparu pendant le runtime.
 */

class EntrepriseTest {

    @ParameterizedTest()
    @CsvSource({
            // Given
            "'2025-02-27', '2025-04-27','2025-03-27'", // Date dans l'intervalle
            "'2025-02-27', '2025-03-27', '2025-02-27'", // Date = Date de début
            "'2025-02-27','2025-03-27','2025-03-27'", // Date = Date de fin
            "'2025-02-27', '2025-02-27', '2025-02-27'" // Date = Date de début = Date de fin
    })
    void testEstDansPlage(LocalDate dateDebut, LocalDate dateFin, LocalDate date) throws WrongDateException {
        // When
        boolean estDansPlage = Entreprise.estDansPlage(date, dateDebut, dateFin);
        // Then
        assertTrue(estDansPlage);

    }

    @ParameterizedTest()
    @CsvSource({
            "'2025-02-27','2025-03-27','2025-04-27'", // Date après la plage
            "'2025-02-27','2025-03-27','2025-01-27'" // Date avant la plage
    })

        // Given
    void testNEstPasDansPlage(LocalDate dateDebut, LocalDate dateFin, LocalDate date) throws WrongDateException {
        // When
        boolean estDansPlage = Entreprise.estDansPlage(date, dateDebut, dateFin);
        // Then
        assertFalse(estDansPlage);
    }

    @ParameterizedTest
    @CsvSource({
            "2025-03-27, 2025-02-27, 2025-03-27"
    })
        // Given
    void testDateDebutEstApresDateDeFin(LocalDate dateDebut, LocalDate dateFin, LocalDate date) {
        // When & Then
        assertThrows(WrongDateException.class, () -> {
            Entreprise.estDansPlage(date, dateDebut, dateFin);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "'2024-11-01'",
            "'2025-04-21'" // Pâques
    })
    // Given
    void testEstJourFerieTrue(LocalDate date) {
        // When
        boolean estJourFerie = Entreprise.estJourFerie(date);
        // Then
        assertTrue(estJourFerie);
    }

    @ParameterizedTest
    @CsvSource({
            "'2025-05-02'"
    })
        // Given
    void testEstJourFerieFalse(LocalDate date) {
        // When
        boolean estJourFerie = Entreprise.estJourFerie(date);
        // Then
        assertFalse(estJourFerie);
    }

    @ParameterizedTest
    @CsvSource({
            "'2025-02-01'"
    })
    // Given
    void testGetPremierJourAnneeDeCongesAvantJuin(LocalDate date) {
        // When
        LocalDate premierJourAnneeDeConges = Entreprise.getPremierJourAnneeDeConges(date);
        // Then
        LocalDate expected = LocalDate.of(2024,6,1);
        assertEquals(expected, premierJourAnneeDeConges);
    }

    @ParameterizedTest
    @CsvSource({
            "'2025-07-04'"
    })
        // Given
    void testGetPremierJourAnneeDeCongesApresJuin(LocalDate date) {
        // When
        LocalDate premierJourAnneeDeConges = Entreprise.getPremierJourAnneeDeConges(date);
        // Then
        LocalDate expected = LocalDate.of(2025,6,1);
        assertEquals(expected, premierJourAnneeDeConges);
    }



}
