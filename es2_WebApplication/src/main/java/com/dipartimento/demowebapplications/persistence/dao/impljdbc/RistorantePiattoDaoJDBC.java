package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.persistence.dao.RistorantePiattoDao;

import java.sql.Connection;
import java.util.List;

public class RistorantePiattoDaoJDBC implements RistorantePiattoDao {

    private final Connection connection;
    private final RistorantePiattoProxy ristorantePiattoProxy;

    public RistorantePiattoDaoJDBC(Connection conn) {
        this.connection = conn;
        this.ristorantePiattoProxy = new RistorantePiattoProxy(conn);
    }

    @Override
    public void addPiattoToRistorante(String ristoranteNome, String piattoNome) {
        ristorantePiattoProxy.addPiattoToRistorante(ristoranteNome, piattoNome);
    }

    @Override
    public void removePiattoFromRistorante(String ristoranteNome, String piattoNome) {
        ristorantePiattoProxy.removePiattoFromRistorante(ristoranteNome, piattoNome);
    }

    @Override
    public List<String> findPiattiByRistorante(String ristoranteNome) {
        return ristorantePiattoProxy.findPiattiByRistorante(ristoranteNome);
    }

    @Override
    public List<String> findRistorantiByPiatto(String piattoNome) {
        return ristorantePiattoProxy.findRistorantiByPiatto(piattoNome);
    }
}
