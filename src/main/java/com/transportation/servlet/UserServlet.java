package com.transportation.servlet;

import com.transportation.dao.UserDAO;
import com.transportation.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            List<User> users = userDAO.readAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
        } else {
            response.sendRedirect("users?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            User user = new User(name, phone);
            userDAO.createUser(user);
            response.sendRedirect("users?action=list");
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            User user = new User(id, name, phone);
            userDAO.updateUser(user);
            response.sendRedirect("users?action=list");
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            userDAO.deleteUser(id);
            response.sendRedirect("users?action=list");
        }
    }
}