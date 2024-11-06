package tn.esprit.tpfoyer.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    FoyerRepository foyerRepository;
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }
    public Foyer retrieveFoyer(Long foyerId) {
        Optional<Foyer> foyerOptional = foyerRepository.findById(foyerId);

        if (foyerOptional.isPresent()) {
            return foyerOptional.get();
        } else {
            // Handle the case where the foyer is not found
            throw new EntityNotFoundException("Foyer not found with ID: " + foyerId);
        }
    }

    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }
    public Foyer modifyFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    public void removeFoyer(Long foyerId) {
        foyerRepository.deleteById(foyerId);
    }
}
