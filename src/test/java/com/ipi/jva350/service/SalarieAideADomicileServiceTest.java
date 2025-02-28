package com.ipi.jva350.service;

import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SalarieAideADomicileServiceTest {

    @Mock
    private SalarieAideADomicileRepository repository;
    @InjectMocks
    private SalarieAideADomicileService service = new SalarieAideADomicileService();

    @Test
    // Given
    public void testCalculeLimiteEntrepriseCongesPermis() {
        // When
        LocalDate moisEnCours = LocalDate.of(2024, 5, 5);
        double congesPayesAcquisAnneeNMoins1 = 10;
        LocalDate moisDebutContrat = LocalDate.of(2024, 5, 5);
        LocalDate premierJourDeConge = LocalDate.of(2024, 7, 5);
        LocalDate dernierJourDeConge = LocalDate.of(2024, 7, 15);
        long limit = service.calculeLimiteEntrepriseCongesPermis(moisEnCours, congesPayesAcquisAnneeNMoins1, moisDebutContrat, premierJourDeConge, dernierJourDeConge);
        //Then
        assertEquals(3, limit);
    }

    @Test
    // Given
    public void testCalculeLimiteEntrepriseCongesPermis10ansAnciennnete() {
        // When
        LocalDate moisEnCours = LocalDate.of(2024, 5, 5);
        double congesPayesAcquisAnneeNMoins1 = 10;
        // on met 10 ans d'ancienneté ici
        LocalDate moisDebutContrat = LocalDate.of(2014, 5, 5);
        LocalDate premierJourDeConge = LocalDate.of(2024, 7, 5);
        LocalDate dernierJourDeConge = LocalDate.of(2024, 7, 15);
        long limit = service.calculeLimiteEntrepriseCongesPermis(moisEnCours, congesPayesAcquisAnneeNMoins1, moisDebutContrat, premierJourDeConge, dernierJourDeConge);
        //Then
        // Ici on vérifie le bonus d'ancienneté
        assertEquals(13, limit);

    }

    // Ce test vérifie que le bonue d'ancienneté est bien de 10 maximum
    @Test
    // Given
    public void testCalculeLimiteEntrepriseCongesPermis15ansAnciennnete() {
        // When
        LocalDate moisEnCours = LocalDate.of(2024, 5, 5);
        double congesPayesAcquisAnneeNMoins1 = 10;
        // on met 10 ans d'ancienneté ici
        LocalDate moisDebutContrat = LocalDate.of(2009, 5, 5);
        LocalDate premierJourDeConge = LocalDate.of(2024, 7, 5);
        LocalDate dernierJourDeConge = LocalDate.of(2024, 7, 15);
        long limit = service.calculeLimiteEntrepriseCongesPermis(moisEnCours, congesPayesAcquisAnneeNMoins1, moisDebutContrat, premierJourDeConge, dernierJourDeConge);
        //Then
        // Ici on vérifie le bonus d'ancienneté
        assertEquals(13, limit);

    }
    
}
