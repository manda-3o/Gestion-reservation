package com.transportation.servlet;

import com.transportation.dao.VehicleDAO;
import com.transportation.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/vehicles")
public class VehicleServlet extends HttpServlet {
    private VehicleDAO vehicleDAO;

    @Override
    public void init() throws ServletException {
        vehicleDAO = new VehicleDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vehicle> vehicles = vehicleDAO.listAllVehicles();
        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("/jsp/vehicles.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String design = request.getParameter("design");
            String type = request.getParameter("type");
            int nbrplace = Integer.parseInt(request.getParameter("nbrplace"));
            double frais = Double.parseDouble(request.getParameter("frais"));

            Vehicle vehicle = new Vehicle(design, type, nbrplace, frais);
            vehicleDAO.createVehicle(vehicle);
            response.sendRedirect("vehicles");
        }
    }
}