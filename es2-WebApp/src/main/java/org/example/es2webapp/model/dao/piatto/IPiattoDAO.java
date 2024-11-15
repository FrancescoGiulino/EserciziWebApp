package org.example.es2webapp.model.dao.piatto;

import org.example.es2webapp.model.entities.Piatto;

import java.util.List;
import java.util.Optional;

public interface IPiattoDAO {
    List<Piatto> findAll();
    Optional<Piatto> findById(long id);
    Piatto save(Piatto piatto);
    void deleteById(long id);
}
