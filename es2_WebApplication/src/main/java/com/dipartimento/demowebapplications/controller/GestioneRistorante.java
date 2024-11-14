package com.dipartimento.demowebapplications.controller;

import com.dipartimento.demowebapplications.model.Ristorante;
import com.dipartimento.demowebapplications.persistence.DBManager;
import com.dipartimento.demowebapplications.persistence.dao.impljdbc.RistoranteDaoJDBC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GestioneRistorante {

    @GetMapping("/elencoRistoranti")
    public ResponseEntity<List<Ristorante>> elencoRistoranti() {
        Connection conn = DBManager.getInstance().getConnection();
        RistoranteDaoJDBC ristoranteDao = new RistoranteDaoJDBC(conn);

        try {
            List<Ristorante> ristoranti = ristoranteDao.findAll();
            if (ristoranti.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ristoranti);
            }
            return ResponseEntity.ok(ristoranti);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/addRistorante")
    public ResponseEntity<String> aggiungiRistorante(@RequestBody Ristorante ristorante) {
        Connection conn = DBManager.getInstance().getConnection();
        RistoranteDaoJDBC ristoranteDao = new RistoranteDaoJDBC(conn);

        try {
            ristoranteDao.save(ristorante); // salva il nuovo ristorante
            return ResponseEntity.status(HttpStatus.CREATED).body("Ristorante aggiunto con successo"); // Status 201 if created
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nell'aggiungere il ristorante"); // Status 500 on error
        }
    }
}
