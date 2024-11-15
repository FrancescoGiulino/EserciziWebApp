package org.example.es2webapp.model.dao.piatto;

import org.example.es2webapp.model.entities.Piatto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PiattoDAOImpl implements IPiattoDAO {
    private final PiattiRepository piattoRepository;
    public PiattoDAOImpl(PiattiRepository piattoRepository) {
        this.piattoRepository = piattoRepository;
    }

    @Override public List<Piatto> findAll() {
        return (List<Piatto>) piattoRepository.findAll();
    }
    @Override public Optional<Piatto> findById(long id) {
        return piattoRepository.findById(id);
    }
    @Override public Piatto save(Piatto ristorante) {
        return piattoRepository.save(ristorante);
    }
    @Override public void deleteById(long id) {
        piattoRepository.deleteById(id);
    }
}
