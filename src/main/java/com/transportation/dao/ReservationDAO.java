package com.transportation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.transportation.model.Reservation;

public class ReservationDAO {
    private final Connection connection;

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }
    
    public ReservationDAO() throws java.sql.SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public void createReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO RESERVER (idreserv, idvoit, idcli, place, date_reserv, date_voyage, payment, montant_avance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, reservation.getIdreserv());
            stmt.setString(2, reservation.getIdvoit());
            stmt.setInt(3, reservation.getIdcli());
            stmt.setInt(4, reservation.getPlace());
            stmt.setTimestamp(5, reservation.getDate_reserv());
            stmt.setDate(6, reservation.getDate_voyage());
            stmt.setString(7, reservation.getPayment());
            stmt.setInt(8, reservation.getMontant_avance());
            stmt.executeUpdate();
        }
    }

    public Reservation readReservation(String idreserv) throws SQLException {
        String sql = "SELECT * FROM RESERVER WHERE idreserv = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idreserv);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservation(
                        rs.getString("idreserv"),
                        rs.getString("idvoit"),
                        rs.getInt("idcli"),
                        rs.getInt("place"),
                        rs.getTimestamp("date_reserv"),
                        rs.getDate("date_voyage"),
                        rs.getString("payment"),
                        rs.getInt("montant_avance")
                    );
                }
            }
        }
        return null;
    }

    public void updateReservation(Reservation reservation) throws SQLException {
        String sql = "UPDATE RESERVER SET idvoit = ?, idcli = ?, place = ?, date_reserv = ?, date_voyage = ?, payment = ?, montant_avance = ? WHERE idreserv = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, reservation.getIdvoit());
            stmt.setInt(2, reservation.getIdcli());
            stmt.setInt(3, reservation.getPlace());
            stmt.setTimestamp(4, reservation.getDate_reserv());
            stmt.setDate(5, reservation.getDate_voyage());
            stmt.setString(6, reservation.getPayment());
            stmt.setInt(7, reservation.getMontant_avance());
            stmt.setString(8, reservation.getIdreserv());
            stmt.executeUpdate();
        }
    }

    public void deleteReservation(String idreserv) throws SQLException {
        String sql = "DELETE FROM RESERVER WHERE idreserv = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idreserv);
            stmt.executeUpdate();
        }
    }

    public List<Reservation> listAll() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM RESERVER";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reservations.add(new Reservation(
                    rs.getString("idreserv"),
                    rs.getString("idvoit"),
                    rs.getInt("idcli"),
                    rs.getInt("place"),
                    rs.getTimestamp("date_reserv"),
                    rs.getDate("date_voyage"),
                    rs.getString("payment"),
                    rs.getInt("montant_avance")
                ));
            }
        }
        return reservations;
    }

    public int getRecetteTotale() throws SQLException {
        String sql = "SELECT SUM(CASE WHEN r.payment = 'tout payé' THEN v.frais ELSE r.montant_avance END) AS total FROM RESERVER r JOIN VOITURE v ON r.idvoit = v.idvoit";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }
}