package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.persistence.dao.RistorantePiattoDao;

import java.sql.Connection;
import java.util.List;

public class RistorantePiattoProxy implements RistorantePiattoDao {

    private final RistorantePiattoDaoJDBC ristorantePiattoDaoJDBC;

    public RistorantePiattoProxy(Connection connection) {
        this.ristorantePiattoDaoJDBC = new RistorantePiattoDaoJDBC(connection);
    }

    @Override
    public void addPiattoToRistorante(String ristoranteNome, String piattoNome) {
        System.out.println("[Proxy] addPiattoToRistorante() invoked for Ristorante: " + ristoranteNome + ", Piatto: " + piattoNome);
        ristorantePiattoDaoJDBC.addPiattoToRistorante(ristoranteNome, piattoNome);
        System.out.println("[Proxy] Piatto added to Ristorante successfully");
    }

    @Override
    public void removePiattoFromRistorante(String ristoranteNome, String piattoNome) {
        System.out.println("[Proxy] removePiattoFromRistorante() invoked for Ristorante: " + ristoranteNome + ", Piatto: " + piattoNome);
        ristorantePiattoDaoJDBC.removePiattoFromRistorante(ristoranteNome, piattoNome);
        System.out.println("[Proxy] Piatto removed from Ristorante successfully");
    }

    @Override
    public List<String> findPiattiByRistorante(String ristoranteNome) {
        System.out.println("[Proxy] findPiattiByRistorante() invoked for Ristorante: " + ristoranteNome);
        List<String> piatti = ristorantePiattoDaoJDBC.findPiattiByRistorante(ristoranteNome);
        System.out.println("[Proxy] Retrieved " + piatti.size() + " piatti for Ristorante: " + ristoranteNome);
        return piatti;
    }

    @Override
    public List<String> findRistorantiByPiatto(String piattoNome) {
        System.out.println("[Proxy] findRistorantiByPiatto() invoked for Piatto: " + piattoNome);
        List<String> ristoranti = ristorantePiattoDaoJDBC.findRistorantiByPiatto(piattoNome);
        System.out.println("[Proxy] Retrieved " + ristoranti.size() + " ristoranti for Piatto: " + piattoNome);
        return ristoranti;
    }
}
