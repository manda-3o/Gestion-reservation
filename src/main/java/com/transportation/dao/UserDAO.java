package com.transportation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.transportation.db.DatabaseConnection;
import com.transportation.model.Client;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public UserDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public int createClient(Client client) throws SQLException {
        String sql = "INSERT INTO CLIENT (nom, numtel) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getNumtel());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setIdcli(generatedKeys.getInt(1));
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }

    public Client readClient(int idcli) throws SQLException {
        String sql = "SELECT * FROM CLIENT WHERE idcli = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idcli);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(resultSet.getInt("idcli"), resultSet.getString("nom"), resultSet.getString("numtel"));
                }
            }
        }
        return null;
    }

    public void updateClient(Client client) throws SQLException {
        String sql = "UPDATE CLIENT SET nom = ?, numtel = ? WHERE idcli = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getNumtel());
            statement.setInt(3, client.getIdcli());
            statement.executeUpdate();
        }
    }

    public void deleteClient(int idcli) throws SQLException {
        String sql = "DELETE FROM CLIENT WHERE idcli = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idcli);
            statement.executeUpdate();
        }
    }

    public List<Client> searchClientByNameOrPhone(String searchTerm) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM CLIENT WHERE nom LIKE ? OR numtel LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String like = "%" + searchTerm + "%";
            statement.setString(1, like);
            statement.setString(2, like);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    clients.add(new Client(resultSet.getInt("idcli"), resultSet.getString("nom"), resultSet.getString("numtel")));
                }
            }
        }
        return clients;
    }

    public Client findClientByPhone(String numtel) throws SQLException {
        String sql = "SELECT * FROM CLIENT WHERE numtel = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, numtel);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(resultSet.getInt("idcli"), resultSet.getString("nom"), resultSet.getString("numtel"));
                }
            }
        }
        return null;
    }
}