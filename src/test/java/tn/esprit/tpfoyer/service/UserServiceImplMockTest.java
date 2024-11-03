package tn.esprit.tpfoyer.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.IBlocService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlocRestControllerTest {

    @Mock
    IBlocService blocService;

    @InjectMocks
    BlocRestController blocRestController;

    private Bloc bloc;
    private List<Bloc> blocs;

    @BeforeEach
    void setUp() {
        bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Bloc A");
        bloc.setCapaciteBloc(100L);

        blocs = new ArrayList<>();
        blocs.add(bloc);
    }

    @Test
    void testGetBlocs() {
        // Simulation du comportement du service
        when(blocService.retrieveAllBlocs()).thenReturn(blocs);

        // Appel de la méthode du contrôleur
        List<Bloc> retrievedBlocs = blocRestController.getBlocs();

        // Vérifications
        assertNotNull(retrievedBlocs);
        assertEquals(1, retrievedBlocs.size());
        verify(blocService, times(1)).retrieveAllBlocs();
    }

    @Test
    void testRetrieveBloc() {
        // Simulation du comportement du service
        when(blocService.retrieveBloc(1L)).thenReturn(bloc);

        // Appel de la méthode du contrôleur
        Bloc retrievedBloc = blocRestController.retrieveBloc(1L);

        // Vérifications
        assertNotNull(retrievedBloc);
        assertEquals(1L, retrievedBloc.getIdBloc());
        assertEquals("Bloc A", retrievedBloc.getNomBloc());
        verify(blocService, times(1)).retrieveBloc(1L);
    }

    @Test
    void testAddBloc() {
        // Simulation du comportement du service
        when(blocService.addBloc(bloc)).thenReturn(bloc);

        // Appel de la méthode du contrôleur
        Bloc addedBloc = blocRestController.addBloc(bloc);

        // Vérifications
        assertNotNull(addedBloc);
        assertEquals(bloc.getNomBloc(), addedBloc.getNomBloc());
        verify(blocService, times(1)).addBloc(bloc);
    }

    @Test
    void testRemoveBloc() {
        // Appel de la méthode du contrôleur
        blocRestController.removeBloc(1L);

        // Vérification que la méthode du service a été appelée
        verify(blocService, times(1)).removeBloc(1L);
    }

    @Test
    void testModifyBloc() {
        // Simulation du comportement du service
        when(blocService.modifyBloc(bloc)).thenReturn(bloc);

        // Appel de la méthode du contrôleur
        Bloc modifiedBloc = blocRestController.modifyBloc(bloc);

        // Vérifications
        assertNotNull(modifiedBloc);
        assertEquals(bloc.getNomBloc(), modifiedBloc.getNomBloc());
        verify(blocService, times(1)).modifyBloc(bloc);
    }

    @Test
    void testGetBlocWithoutFoyer() {
        // Simulation du comportement du service
        when(blocService.trouverBlocsSansFoyer()).thenReturn(blocs);

        // Appel de la méthode du contrôleur
        List<Bloc> retrievedBlocs = blocRestController.getBlocswirhoutFoyer();

        // Vérifications
        assertNotNull(retrievedBlocs);
        assertEquals(1, retrievedBlocs.size());
        verify(blocService, times(1)).trouverBlocsSansFoyer();
    }

    @Test
    void testRecuperBlocsParNomEtCap() {
        // Simulation du comportement du service
        when(blocService.trouverBlocsParNomEtCap("Bloc A", 100L)).thenReturn(blocs);

        // Appel de la méthode du contrôleur
        List<Bloc> retrievedBlocs = blocRestController.recuperBlocsParNomEtCap("Bloc A", 100L);

        // Vérifications
        assertNotNull(retrievedBlocs);
        assertEquals(1, retrievedBlocs.size());
        assertEquals("Bloc A", retrievedBlocs.get(0).getNomBloc());
        assertEquals(100L, retrievedBlocs.get(0).getCapaciteBloc());
        verify(blocService, times(1)).trouverBlocsParNomEtCap("Bloc A", 100L);
    }
}
