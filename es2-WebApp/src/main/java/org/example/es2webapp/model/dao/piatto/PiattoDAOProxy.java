package org.example.es2webapp.model.dao.piatto;

import org.example.es2webapp.model.entities.Piatto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class PiattoDAOProxy implements IPiattoDAO {
    private static final Logger logger = LoggerFactory.getLogger(org.example.es2webapp.model.dao.piatto.PiattoDAOProxy.class);
    private final PiattoDAOImpl piattoDAO;
    private final Map<Long, Piatto> cache = new HashMap<>();

    public PiattoDAOProxy(PiattoDAOImpl piattoDAO) {
        this.piattoDAO = piattoDAO;
    }

    @Override public List<Piatto> findAll() {
        logger.info("Recupero tutti i piatti");
        return piattoDAO.findAll();
    }

    @Override public Optional<Piatto> findById(long id) {
        if (cache.containsKey(id)) {
            logger.info("Piatto con ID {} trovato nella cache", id);
            return Optional.ofNullable(cache.get(id));
        }
        logger.info("Piatto con ID {} non trovato nella cache, esecuzione query", id);
        Optional<Piatto> piatto = piattoDAO.findById(id);
        piatto.ifPresent(value -> cache.put(id, value));
        return piatto;
    }

    @Override public Piatto save(Piatto piatto) {
        logger.info("Salvataggio ristorante: {}", piatto.getNome());
        Piatto savedPiatto = piattoDAO.save(piatto);
        cache.put(savedPiatto.getId(), savedPiatto);
        return savedPiatto;
    }

    @Override public void deleteById(long id) {
        logger.info("Eliminazione ristorante con ID {}", id);
        piattoDAO.deleteById(id);
        cache.remove(id);
    }
}
