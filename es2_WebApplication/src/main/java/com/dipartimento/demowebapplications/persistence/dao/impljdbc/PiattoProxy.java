package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.persistence.dao.PiattoDao;

import java.sql.Connection;
import java.util.List;

public class PiattoProxy implements PiattoDao {

    private final PiattoDaoJDBC piattoDaoJDBC;

    public PiattoProxy(Connection connection) {
        this.piattoDaoJDBC = new PiattoDaoJDBC(connection);
    }

    @Override
    public List<Piatto> findAll() {
        System.out.println("[Proxy] findAll() invoked");
        List<Piatto> result = piattoDaoJDBC.findAll();
        System.out.println("[Proxy] Retrieved " + result.size() + " piatti");
        return result;
    }

    @Override
    public Piatto findByPrimaryKey(String nome) {
        System.out.println("[Proxy] findByPrimaryKey() invoked with nome: " + nome);
        Piatto result = piattoDaoJDBC.findByPrimaryKey(nome);
        if (result != null) {
            System.out.println("[Proxy] Piatto found: " + result.getNome());
        } else {
            System.out.println("[Proxy] No Piatto found with nome: " + nome);
        }
        return result;
    }

    @Override
    public void save(Piatto piatto) {
        System.out.println("[Proxy] save() invoked for Piatto: " + piatto.getNome());
        piattoDaoJDBC.save(piatto);
        System.out.println("[Proxy] Piatto saved/updated successfully");
    }

    @Override
    public void delete(Piatto piatto) {
        System.out.println("[Proxy] delete() invoked for Piatto: " + piatto.getNome());
        piattoDaoJDBC.delete(piatto);
        System.out.println("[Proxy] Piatto deleted successfully");
    }

    @Override
    public List<Piatto> findAllByRistoranteName(String ristoranteNome) {
        System.out.println("[Proxy] findAllByRistoranteName() invoked for Ristorante: " + ristoranteNome);
        List<Piatto> result = piattoDaoJDBC.findAllByRistoranteName(ristoranteNome);
        System.out.println("[Proxy] Retrieved " + result.size() + " piatti for Ristorante: " + ristoranteNome);
        return result;
    }
}
