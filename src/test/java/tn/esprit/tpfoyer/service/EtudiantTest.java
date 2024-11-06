package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.Test;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.entity.Reservation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class EtudiantTest {

    @Test
    void testEtudiantDefaultConstructor() {
        Etudiant etudiant = new Etudiant();

        assertThat(etudiant.getIdEtudiant()).isZero();
        assertThat(etudiant.getNomEtudiant()).isNull();
        assertThat(etudiant.getPrenomEtudiant()).isNull();
        assertThat(etudiant.getCinEtudiant()).isZero();
        assertThat(etudiant.getDateNaissance()).isNull();
        assertThat(etudiant.getReservations()).isNull();
    }

    @Test
    void testEtudiantParameterizedConstructor() {
        Date dateNaissance = new Date();
        Set<Reservation> reservations = new HashSet<>();
        Etudiant etudiant = new Etudiant(1L, "Dupont", "Jean", 12345678L, dateNaissance, reservations);

        assertThat(etudiant.getIdEtudiant()).isEqualTo(1L);
        assertThat(etudiant.getNomEtudiant()).isEqualTo("Dupont");
        assertThat(etudiant.getPrenomEtudiant()).isEqualTo("Jean");
        assertThat(etudiant.getCinEtudiant()).isEqualTo(12345678L);
        assertThat(etudiant.getDateNaissance()).isEqualTo(dateNaissance);
        assertThat(etudiant.getReservations()).isEqualTo(reservations);
    }

    @Test
    void testSettersAndGetters() {
        Etudiant etudiant = new Etudiant();

        etudiant.setIdEtudiant(2L);
        etudiant.setNomEtudiant("Doe");
        etudiant.setPrenomEtudiant("John");
        etudiant.setCinEtudiant(87654321L);
        Date dateNaissance = new Date();
        etudiant.setDateNaissance(dateNaissance);
        Set<Reservation> reservations = new HashSet<>();
        etudiant.setReservations(reservations);

        assertThat(etudiant.getIdEtudiant()).isEqualTo(2L);
        assertThat(etudiant.getNomEtudiant()).isEqualTo("Doe");
        assertThat(etudiant.getPrenomEtudiant()).isEqualTo("John");
        assertThat(etudiant.getCinEtudiant()).isEqualTo(87654321L);
        assertThat(etudiant.getDateNaissance()).isEqualTo(dateNaissance);
        assertThat(etudiant.getReservations()).isEqualTo(reservations);
    }

    @Test
    void testToString() {
        Date dateNaissance = new Date();
        Etudiant etudiant = new Etudiant(3L, "Martin", "Pierre", 23456789L, dateNaissance, new HashSet<>());

        assertThat(etudiant.toString()).contains("Martin")
                .contains("Pierre")
                .contains("23456789")
                .contains(dateNaissance.toString());
    }
}
