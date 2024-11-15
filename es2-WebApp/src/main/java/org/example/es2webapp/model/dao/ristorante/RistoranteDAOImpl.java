package org.example.es2webapp.model.dao.ristorante;

import org.example.es2webapp.model.entities.Ristorante;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RistoranteDAOImpl implements IRistoranteDAO {
    private final RistorantiRepository ristorantiRepository;
    public RistoranteDAOImpl(RistorantiRepository ristorantiRepository) {
        this.ristorantiRepository = ristorantiRepository;
    }

    @Override public List<Ristorante> findAll() {
        return (List<Ristorante>) ristorantiRepository.findAll();
    }
    @Override public Optional<Ristorante> findById(long id) {
        return ristorantiRepository.findById(id);
    }
    @Override public Ristorante save(Ristorante ristorante) {
        return ristorantiRepository.save(ristorante);
    }
    @Override public void deleteById(long id) {
        ristorantiRepository.deleteById(id);
    }
}
