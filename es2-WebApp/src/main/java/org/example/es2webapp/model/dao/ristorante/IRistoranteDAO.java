package org.example.es2webapp.model.dao.ristorante;

import org.example.es2webapp.model.entities.Ristorante;
import java.util.List;
import java.util.Optional;

public interface IRistoranteDAO {
    List<Ristorante> findAll();
    Optional<Ristorante> findById(long id);
    Ristorante save(Ristorante ristorante);
    void deleteById(long id);
}
