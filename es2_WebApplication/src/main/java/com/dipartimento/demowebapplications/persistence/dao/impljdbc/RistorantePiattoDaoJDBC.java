package com.dipartimento.demowebapplications.persistence.dao.impljdbc;
import com.dipartimento.demowebapplications.persistence.dao.RistorantePiattoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RistorantePiattoDaoJDBC implements RistorantePiattoDao {
    private Connection connection;

    public RistorantePiattoDaoJDBC(Connection conn) {
        this.connection = conn;
    }

    @Override
    public void addPiattoToRistorante(String ristoranteNome, String piattoNome) {
        String query = "INSERT INTO ristorante_piatto (ristorante_nome, piatto_nome) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ristoranteNome);
            statement.setString(2, piattoNome);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePiattoFromRistorante(String ristoranteNome, String piattoNome) {
        String query = "DELETE FROM ristorante_piatto WHERE ristorante_nome = ? AND piatto_nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ristoranteNome);
            statement.setString(2, piattoNome);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> findPiattiByRistorante(String ristoranteNome) {
        List<String> piatti = new ArrayList<>();
        String query = "SELECT piatto_nome FROM ristorante_piatto WHERE ristorante_nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ristoranteNome);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                piatti.add(resultSet.getString("piatto_nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piatti;
    }

    @Override
    public List<String> findRistorantiByPiatto(String piattoNome) {
        List<String> ristoranti = new ArrayList<>();
        String query = "SELECT ristorante_nome FROM ristorante_piatto WHERE piatto_nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, piattoNome);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ristoranti.add(resultSet.getString("ristorante_nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ristoranti;
    }
}
