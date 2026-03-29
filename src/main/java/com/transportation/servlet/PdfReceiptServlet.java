package com.transportation.servlet;

import com.transportation.dao.ReservationDAO;
import com.transportation.dao.UserDAO;
import com.transportation.dao.VehicleDAO;
import com.transportation.db.DatabaseConnection;
import com.transportation.model.Client;
import com.transportation.model.Reservation;
import com.transportation.model.Voiture;
import com.transportation.util.PdfReceiptGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/recu-pdf")
public class PdfReceiptServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idreserv = req.getParameter("idreserv");
        if (idreserv == null || idreserv.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID réservation manquant");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            ReservationDAO reservationDAO = new ReservationDAO(conn);
            Reservation reservation = reservationDAO.readReservation(idreserv);

            if (reservation == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Réservation introuvable");
                return;
            }

            UserDAO userDAO = new UserDAO(conn);
            Client client = userDAO.readUser(reservation.getIdcli());
            VehicleDAO vehicleDAO = new VehicleDAO(conn);
            Voiture voiture = vehicleDAO.readVehicle(reservation.getIdvoit());

            int reste = voiture != null ? reservation.calculerReste(voiture.getFrais()) : 0;

            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition", "attachment; filename=recu_" + idreserv + ".pdf");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfReceiptGenerator.generateReceiptPdf(baos, reservation, client, voiture, reste);

            resp.setContentLength(baos.size());
            baos.writeTo(resp.getOutputStream());
            resp.getOutputStream().flush();

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
