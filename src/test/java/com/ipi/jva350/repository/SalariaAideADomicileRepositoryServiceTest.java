package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SalariaAideADomicileRepositoryServiceTest {

    @Autowired
    SalarieAideADomicileRepository salarieAideADomicileRepository;

    // TestEntityManager allows to save fake data to simulate an operational database.
    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp() {

        // Set up the fake database data
        SalarieAideADomicile sa1 = new SalarieAideADomicile();
        sa1.setNom("Dupond");
        sa1.setCongesPayesPrisAnneeNMoins1(20);
        sa1.setCongesPayesAcquisAnneeNMoins1(30);

        SalarieAideADomicile sa2 = new SalarieAideADomicile();
        sa2.setNom("Bertrand");
        sa2.setCongesPayesPrisAnneeNMoins1(10);
        sa2.setCongesPayesAcquisAnneeNMoins1(30);

        // Prepare to save the data
        testEntityManager.persist(sa1);
        testEntityManager.persist(sa2);
        // Check if the data repect the database constraint
        testEntityManager.flush();
    }

    @Test
    // Given
    public void testFindByNom() {
        // When
        SalarieAideADomicile salarie = salarieAideADomicileRepository.findByNom("Bertrand");
        // Then
        assertEquals("Bertrand", salarie.getNom());
    }

    @Test
    // Given
    public void testPartCongesPrisTotauxAnneeNMoins1() {
        // When
        Double part = salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1();
        // Then
        assertEquals(0.5, part);
    }


}
