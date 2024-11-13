package com.dipartimento.demowebapplications.controller;

import com.dipartimento.demowebapplications.model.Ristorante;
import com.dipartimento.demowebapplications.persistence.DBManager;
import com.dipartimento.demowebapplications.persistence.dao.impljdbc.RistoranteDaoJDBC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.List;

@RestController
public class GestioneRistorante {

    @GetMapping("/elencoRistoranti")
    public List<Ristorante> elencoRistoranti() {
        Connection conn = DBManager.getInstance().getConnection(); //ottieni la connessione al database
        RistoranteDaoJDBC ristoranteDao = new RistoranteDaoJDBC(conn);

        try {
            return ristoranteDao.findAll(); //restituisce direttamente la lista dei ristoranti
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/addRistorante")
    public String aggiungiRistorante(
            @RequestParam String nome,
            @RequestParam String descrizione,
            @RequestParam String ubicazione) {

        Ristorante ristorante = new Ristorante();
        ristorante.setNome(nome);
        ristorante.setDescrizione(descrizione);
        ristorante.setUbicazione(ubicazione);

        Connection conn = DBManager.getInstance().getConnection();
        RistoranteDaoJDBC ristoranteDao = new RistoranteDaoJDBC(conn);

        try {
            ristoranteDao.save(ristorante); //salva il nuovo ristorante
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Errore nell'aggiungere il ristorante";
        }
    }
}
