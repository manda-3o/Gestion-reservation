package com.transportation.servlet;

import com.transportation.dao.ReservationDAO;
import com.transportation.dao.UserDAO;
import com.transportation.dao.VehicleDAO;
import com.transportation.db.DatabaseConnection;
import com.transportation.model.Client;
import com.transportation.model.Reservation;
import com.transportation.model.Voiture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/reservation")
public class ReservationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idreserv = req.getParameter("idreserv");
        String idvoit = req.getParameter("idvoit");
        String nom = req.getParameter("nom");
        String numtel = req.getParameter("numtel");
        int place = Integer.parseInt(req.getParameter("place"));
        Timestamp dateReserv = Timestamp.valueOf(req.getParameter("date_reserv"));
        Date dateVoyage = Date.valueOf(req.getParameter("date_voyage"));
        String payment = req.getParameter("payment");
        int montantAvance = Integer.parseInt(req.getParameter("montant_avance"));

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            UserDAO userDAO = new UserDAO(conn);
            ReservationDAO reservationDAO = new ReservationDAO(conn);
            VehicleDAO vehicleDAO = new VehicleDAO(conn);

            Client client = userDAO.findClientByPhone(numtel);
            int idcli;
            if (client == null) {
                idcli = userDAO.createUser(new Client(0, nom, numtel));
            } else {
                idcli = client.getIdcli();
                if (!client.getNom().equals(nom)) {
                    client.setNom(nom);
                    userDAO.updateUser(client);
                }
            }

            Reservation reservation = new Reservation(idreserv, idvoit, idcli, place, dateReserv, dateVoyage, payment, montantAvance);
            reservationDAO.createReservation(reservation);

            String updatePlace = "UPDATE PLACE SET occupation = 'oui' WHERE idvoit = ? AND place = ?";
            try (java.sql.PreparedStatement st = conn.prepareStatement(updatePlace)) {
                st.setString(1, idvoit);
                st.setInt(2, place);
                st.executeUpdate();
            }

            Voiture voiture = vehicleDAO.readVehicle(idvoit);
            int frais = voiture != null ? voiture.getFrais() : 0;
            int reste = reservation.calculerReste(frais);

            conn.commit();

            req.setAttribute("reservation", reservation);
            req.setAttribute("client", new Client(idcli, nom, numtel));
            req.setAttribute("voiture", voiture);
            req.setAttribute("reste", reste);
            req.getRequestDispatcher("/recu.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Erreur lors de la création de la réservation", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try (Connection conn = DatabaseConnection.getConnection()) {
            ReservationDAO reservationDAO = new ReservationDAO(conn);
            VehicleDAO vehicleDAO = new VehicleDAO(conn);
            List<Reservation> all = reservationDAO.listAll();

            if ("report".equalsIgnoreCase(action)) {
                String filtre = req.getParameter("filtre");

                List<Reservation> result = all.stream().filter(r -> {
                    Voiture v = vehicleDAO.readVehicle(r.getIdvoit());
                    if (v == null) return false;
                    int frais = v.getFrais();
                    int avance = r.getMontant_avance();

                    if ("paye".equalsIgnoreCase(filtre)) {
                        return avance == frais;
                    } else if ("avance".equalsIgnoreCase(filtre)) {
                        return avance > 0 && avance < frais;
                    } else if ("reste".equalsIgnoreCase(filtre)) {
                        return avance == 0;
                    }
                    return true;
                }).toList();

                req.setAttribute("reservations", result);
                req.setAttribute("filtre", filtre);
                req.getRequestDispatcher("/report.jsp").forward(req, resp);
            } else {
                req.setAttribute("reservations", all);
                req.getRequestDispatcher("/reservations.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}