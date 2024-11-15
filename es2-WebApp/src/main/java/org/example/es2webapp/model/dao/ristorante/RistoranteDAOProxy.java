package org.example.es2webapp.model.dao.ristorante;

import org.example.es2webapp.model.entities.Ristorante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class RistoranteDAOProxy implements IRistoranteDAO {
    private static final Logger logger = LoggerFactory.getLogger(RistoranteDAOProxy.class);
    private final RistoranteDAOImpl ristoranteDAO;
    private final Map<Long, Ristorante> cache = new HashMap<>();

    public RistoranteDAOProxy(RistoranteDAOImpl ristoranteDAO) {
        this.ristoranteDAO = ristoranteDAO;
    }

    @Override public List<Ristorante> findAll() {
        logger.info("Recupero tutti i ristoranti");
        return ristoranteDAO.findAll();
    }

    @Override public Optional<Ristorante> findById(long id) {
        if (cache.containsKey(id)) {
            logger.info("Ristorante con ID {} trovato nella cache", id);
            return Optional.ofNullable(cache.get(id));
        }
        logger.info("Ristorante con ID {} non trovato nella cache, esecuzione query", id);
        Optional<Ristorante> ristorante = ristoranteDAO.findById(id);
        ristorante.ifPresent(value -> cache.put(id, value));
        return ristorante;
    }

    @Override public Ristorante save(Ristorante ristorante) {
        logger.info("Salvataggio ristorante: {}", ristorante.getNome());
        Ristorante savedRistorante = ristoranteDAO.save(ristorante);
        cache.put(savedRistorante.getId(), savedRistorante);
        return savedRistorante;
    }

    @Override public void deleteById(long id) {
        logger.info("Eliminazione ristorante con ID {}", id);
        ristoranteDAO.deleteById(id);
        cache.remove(id);
    }
}
