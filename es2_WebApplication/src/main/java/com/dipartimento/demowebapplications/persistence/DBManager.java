package com.dipartimento.demowebapplications.persistence;

import com.dipartimento.demowebapplications.persistence.dao.PiattoDao;
import com.dipartimento.demowebapplications.persistence.dao.RistoranteDao;
import com.dipartimento.demowebapplications.persistence.dao.RistorantePiattoDao;
import com.dipartimento.demowebapplications.persistence.dao.impljdbc.PiattoDaoJDBC;
import com.dipartimento.demowebapplications.persistence.dao.impljdbc.RistoranteDaoJDBC;
import com.dipartimento.demowebapplications.persistence.dao.impljdbc.RistorantePiattoDaoJDBC;

import java.sql.*;

public class DBManager {
    private static DBManager instance = null;

    private DBManager(){}
    private RistoranteDao ristoranteDao = null;
    private PiattoDao piattoDao = null;
    private RistorantePiattoDao ristorantePiattoDao = null;

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public RistorantePiattoDao getRistorantePiattoDao() {
        if (ristorantePiattoDao == null) {
            ristorantePiattoDao = new RistorantePiattoDaoJDBC(getConnection());
        }
        return ristorantePiattoDao;
    }

    Connection con = null;

    public Connection getConnection() {
        System.out.println("STO PROVANDO A CONNETTERMI AL DB...");
        String url = "jdbc:postgresql://localhost:5432/ristoranti"; //nome del DB
        String username = "postgres"; //nome utente
        String password = "remi5"; //password

        if (con == null) {
            try {
                con = DriverManager.getConnection(url, username, password);
                System.out.println("Connessione riuscita!");
            } catch (SQLException e) {
                System.err.println("Errore di connessione: " + e.getMessage());
            }
        }
        return con;
    }

    public RistoranteDao getRistoranteDao(){
        if (ristoranteDao == null) {
            System.out.println("Ristorante DAO --> getRistoranteDao()");
            ristoranteDao = new RistoranteDaoJDBC(getConnection());
        }
        return  ristoranteDao;
    }

    public PiattoDao getPiattoDao(){
        if (piattoDao == null) {
            System.out.println("Piatto DAO --> getPiattoDao()");
            piattoDao = new PiattoDaoJDBC(getConnection());
        }
        return  piattoDao;
    }

    public static void main(String[] args) {
        //MAIN DI PROVA, PER TESTARE IL FUNZIONAMENTO DELLA CLASSE
        System.out.println("Tentativo di connessione al DB...");
        Connection con = DBManager.getInstance().getConnection();
        try {
            Statement st = con.createStatement();
            //cambia la query se necessario, per esempio sulla tabella "piatto"
            ResultSet rs = st.executeQuery("SELECT * FROM piatto");

            while (rs.next()) {
                //stampa ogni riga della tabella "piatto"
                System.out.println("Nome piatto: " + rs.getString("nome") + ", Ingredienti: " + rs.getString("ingredienti"));
            }
        } catch (SQLException e) {
            System.err.println("Errore durante l'esecuzione della query: " + e.getMessage());
        }
    }

}
