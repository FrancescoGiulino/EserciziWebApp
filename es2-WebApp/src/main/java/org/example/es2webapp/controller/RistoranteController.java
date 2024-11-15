package org.example.es2webapp.controller;

import org.example.es2webapp.model.dao.piatto.IPiattoDAO;
import org.example.es2webapp.model.dao.ristorante.IRistoranteDAO;
import org.example.es2webapp.model.entities.Piatto;
import org.example.es2webapp.model.entities.Ristorante;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ristoranti")
public class RistoranteController {

    private final IRistoranteDAO ristoranteDAO;
    private final IPiattoDAO piattoDAO;

    public RistoranteController(
            @Qualifier("ristoranteDAOProxy") IRistoranteDAO ristoranteDAO,
            @Qualifier("piattoDAOProxy") IPiattoDAO piattoDAO) {
        this.ristoranteDAO = ristoranteDAO;
        this.piattoDAO = piattoDAO;
    }

    @GetMapping
    public List<Ristorante> getRistoranti() {
        return ristoranteDAO.findAll();
    }

    @GetMapping("/{ristoranteId}")
    public Ristorante getRistorante(@PathVariable long ristoranteId) {
        return ristoranteDAO.findById(ristoranteId).orElseThrow();
    }

    @PostMapping
    public Ristorante createRistorante(@RequestBody Ristorante newRistorante) {
        return ristoranteDAO.save(newRistorante);
    }

    @PutMapping("/{ristoranteId}")
    public Ristorante updateRistorante(@PathVariable long ristoranteId, @RequestBody Ristorante ristoranteDto) {
        Ristorante ristoranteToUpdate = ristoranteDAO.findById(ristoranteId).orElseThrow();
        ristoranteToUpdate.setNome(ristoranteDto.getNome());
        ristoranteToUpdate.setDescrizione(ristoranteDto.getDescrizione());
        ristoranteToUpdate.setUbicazione(ristoranteDto.getUbicazione());
        return ristoranteDAO.save(ristoranteToUpdate);
    }

    @DeleteMapping("/{ristoranteId}")
    public void deleteRistorante(@PathVariable long ristoranteId) {
        ristoranteDAO.deleteById(ristoranteId);
    }

    // Endpoint per aggiungere un piatto a un ristorante
    @PostMapping("/{ristoranteId}/piatti/{piattoId}")
    public Ristorante addPiattoToRistorante(@PathVariable long ristoranteId, @PathVariable long piattoId) {
        Ristorante ristorante = ristoranteDAO.findById(ristoranteId).orElseThrow();
        Piatto piatto = piattoDAO.findById(piattoId).orElseThrow();
        ristorante.addPiatto(piatto);
        return ristoranteDAO.save(ristorante);
    }

    // Endpoint per rimuovere un piatto da un ristorante
    @DeleteMapping("/{ristoranteId}/piatti/{piattoId}")
    public Ristorante removePiattoFromRistorante(@PathVariable long ristoranteId, @PathVariable long piattoId) {
        Ristorante ristorante = ristoranteDAO.findById(ristoranteId).orElseThrow();
        Piatto piatto = piattoDAO.findById(piattoId).orElseThrow();
        ristorante.removePiatto(piatto);
        return ristoranteDAO.save(ristorante);
    }
}

