package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.persistence.dao.PiattoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiattoDaoJDBC implements PiattoDao {

    private final Connection connection;
    private final PiattoProxy piattoProxy;

    public PiattoDaoJDBC(Connection conn) {
        this.connection = conn;
        this.piattoProxy = new PiattoProxy(conn); // Usa PiattoProxy come proxy
    }

    @Override
    public List<Piatto> findAll() {
        // Usa il Proxy per chiamare findAll()
        return piattoProxy.findAll();
    }

    @Override
    public Piatto findByPrimaryKey(String nome) {
        // Usa il Proxy per chiamare findByPrimaryKey()
        return piattoProxy.findByPrimaryKey(nome);
    }

    @Override
    public void save(Piatto piatto) {
        // Usa il Proxy per chiamare save()
        piattoProxy.save(piatto);
    }

    @Override
    public void delete(Piatto piatto) {
        // Usa il Proxy per chiamare delete()
        piattoProxy.delete(piatto);
    }

    @Override
    public List<Piatto> findAllByRistoranteName(String ristoranteNome) {
        // Usa il Proxy per chiamare findAllByRistoranteName()
        return piattoProxy.findAllByRistoranteName(ristoranteNome);
    }
}
