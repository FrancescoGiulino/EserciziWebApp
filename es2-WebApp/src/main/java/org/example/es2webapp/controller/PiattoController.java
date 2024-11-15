package org.example.es2webapp.controller;

import org.example.es2webapp.model.dao.piatto.IPiattoDAO;
import org.example.es2webapp.model.entities.Piatto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/piatti")
public class PiattoController {

    private final IPiattoDAO piattoDAO;

    public PiattoController(@Qualifier("piattoDAOProxy") IPiattoDAO piattoDAO) {
        this.piattoDAO = piattoDAO;
    }

    @GetMapping public List<Piatto> getPiatto() {
        return piattoDAO.findAll();
    }

    @GetMapping("/{piattoId}") public Piatto getPiatto(@PathVariable long piattoId) {
        return piattoDAO.findById(piattoId).orElseThrow();
    }

    @PostMapping public Piatto createPiatto(@RequestBody Piatto newPiatto) {
        return piattoDAO.save(newPiatto);
    }

    @PutMapping("/{piattoId}") public Piatto updatePiatto(@PathVariable long piattoId, @RequestBody Piatto piattoDto) {
        Piatto piattoToUpdate = piattoDAO.findById(piattoId).orElseThrow();
        piattoToUpdate.setNome(piattoDto.getNome());
        piattoToUpdate.setDescrizione(piattoDto.getDescrizione());
        return piattoDAO.save(piattoToUpdate);
    }

    @DeleteMapping("/{piattoId}") public void deletePiatto(@PathVariable long piattoId) {
        piattoDAO.deleteById(piattoId);
    }
}
