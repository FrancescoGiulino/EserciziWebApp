package com.dipartimento.demowebapplications.persistence.dao;

import java.util.List;

public interface RistorantePiattoDao {
    void addPiattoToRistorante(String ristoranteNome, String piattoNome);
    void removePiattoFromRistorante(String ristoranteNome, String piattoNome);
    List<String> findPiattiByRistorante(String ristoranteNome);
    List<String> findRistorantiByPiatto(String piattoNome);
}
