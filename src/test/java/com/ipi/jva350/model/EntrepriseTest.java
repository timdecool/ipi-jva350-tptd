package com.ipi.jva350.model;

import com.ipi.jva350.exception.WrongDateException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EntrepriseTest {

    @ParameterizedTest()
    @CsvSource({
            "'2025-02-27', '2025-04-27','2025-03-27'"
    })

        // Given
    void testEstDansPlage(LocalDate dateDebut, LocalDate dateFin, LocalDate date) throws WrongDateException {
        // When
        boolean estDansPlage = Entreprise.estDansPlage(date, dateDebut, dateFin);
        // Then
        assertTrue(estDansPlage);

    }

    @ParameterizedTest()
    @CsvSource({
            "'2025-02-27','2025-03-27','2025-04-27'"
    })

        // Given
    void testNEstPasDansPlage(LocalDate dateDebut, LocalDate dateFin, LocalDate date) throws WrongDateException {
        // When
        boolean estDansPlage = Entreprise.estDansPlage(date, dateDebut, dateFin);
        // Then
        assertFalse(estDansPlage);

    }

    @ParameterizedTest()
    @CsvSource({
            "'2025-02-27','2025-03-27','2025-03-27'"
    })

        // Given
    void testDateEgalDateFin(LocalDate dateDebut, LocalDate dateFin, LocalDate date) throws WrongDateException {
        // When
        boolean estDansPlage = Entreprise.estDansPlage(date, dateDebut, dateFin);
        // Then
        assertTrue(estDansPlage);

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

}
