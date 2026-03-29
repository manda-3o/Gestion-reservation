package com.transportation.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.transportation.dao.VehicleDAO;
import com.transportation.db.DatabaseConnection;
import com.transportation.model.Place;

@WebServlet("/places-disponibles")
public class PlaceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idvoit = req.getParameter("idvoit");
        if (idvoit == null || idvoit.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            VehicleDAO vehicleDAO = new VehicleDAO(conn);
            List<Place> places = vehicleDAO.getPlacesLibres(idvoit);
            resp.setContentType("application/json");

            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < places.size(); i++) {
                Place p = places.get(i);
                json.append("{\"idvoit\":\"").append(p.getIdvoit()).append("\",\"place\":").append(p.getPlace()).append("}");
                if (i < places.size() - 1) json.append(",");
            }
            json.append("]");
            resp.getWriter().write(json.toString());
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
