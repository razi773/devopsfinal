package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UniversityServiceImplMockTest {

    @Mock
    UniversiteRepository universiteRepository;

    @InjectMocks
    UniversiteServiceImpl universiteService;

    Universite universite = new Universite("f1", "l1", "adresse1");

    List<Universite> listUsers = new ArrayList<Universite>() {
        {
            add(new Universite("f2", "nom1", "adresse2"));
            add(new Universite("f3", "nom2", "adresse3"));
        }
    };

    @Test
     void testRetrieveUser() {
        Mockito.when(universiteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(universite));
        Universite user1 = universiteService.retrieveUniversite(2L);
        Assertions.assertNotNull(user1);
    }

    // Nouveau test pour la classe Bloc
    @Test
     void testBlocChambresAssociation() {
        // Création d'un bloc
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");

        // Création de chambres
        Chambre chambre1 = new Chambre();
        chambre1.setBloc(bloc);

        Chambre chambre2 = new Chambre();
        chambre2.setBloc(bloc);

        // Ajout des chambres au bloc
        Set<Chambre> chambres = new HashSet<>();
        chambres.add(chambre1);
        chambres.add(chambre2);

        bloc.setChambres(chambres);

        // Vérification que les chambres sont associées au bloc
        Assertions.assertNotNull(bloc.getChambres());
        Assertions.assertEquals(2, bloc.getChambres().size());
        Assertions.assertTrue(bloc.getChambres().contains(chambre1));
        Assertions.assertTrue(bloc.getChambres().contains(chambre2));
    }
}
