package com.transportation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.transportation.model.Place;
import com.transportation.model.Voiture;

public class VehicleDAO {
    private final Connection connection;

    public VehicleDAO(Connection connection) {
        this.connection = connection;
    }
    
    public VehicleDAO() throws java.sql.SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public void createVehicle(Voiture voiture) throws SQLException {
        String sql = "INSERT INTO VOITURE (idvoit, Design, type, nbrplace, frais) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, voiture.getIdvoit());
            stmt.setString(2, voiture.getDesign());
            stmt.setString(3, voiture.getType());
            stmt.setInt(4, voiture.getNbrplace());
            stmt.setInt(5, voiture.getFrais());
            stmt.executeUpdate();
        }
    }

    public Voiture readVehicle(String idvoit) throws SQLException {
        String sql = "SELECT * FROM VOITURE WHERE idvoit = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idvoit);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Voiture(rs.getString("idvoit"), rs.getString("Design"), rs.getString("type"), rs.getInt("nbrplace"), rs.getInt("frais"));
                }
            }
        }
        return null;
    }

    public void updateVehicle(Voiture voiture) throws SQLException {
        String sql = "UPDATE VOITURE SET Design = ?, type = ?, nbrplace = ?, frais = ? WHERE idvoit = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, voiture.getDesign());
            stmt.setString(2, voiture.getType());
            stmt.setInt(3, voiture.getNbrplace());
            stmt.setInt(4, voiture.getFrais());
            stmt.setString(5, voiture.getIdvoit());
            stmt.executeUpdate();
        }
    }

    public void deleteVehicle(String idvoit) throws SQLException {
        String sql = "DELETE FROM VOITURE WHERE idvoit = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idvoit);
            stmt.executeUpdate();
        }
    }

    public List<Voiture> listAll() throws SQLException {
        List<Voiture> cars = new ArrayList<>();
        String sql = "SELECT * FROM VOITURE";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cars.add(new Voiture(rs.getString("idvoit"), rs.getString("Design"), rs.getString("type"), rs.getInt("nbrplace"), rs.getInt("frais")));
            }
        }
        return cars;
    }

    public List<Place> getPlacesLibres(String idvoit) throws SQLException {
        List<Place> libres = new ArrayList<>();
        String sql = "SELECT * FROM PLACE WHERE idvoit = ? AND occupation = 'non'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idvoit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    libres.add(new Place(rs.getString("idvoit"), rs.getInt("place"), rs.getString("occupation")));
                }
            }
        }
        return libres;
    }
}