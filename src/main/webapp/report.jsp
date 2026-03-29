<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.transportation.model.Reservation" %>
<%@ page import="java.util.List" %>

<%
    List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
    if (reservations == null) {
        reservations = new java.util.ArrayList<>();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Rapport voyageurs</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
</head>
<body>
<div class="container mt-3">
    <h2>Rapport des voyageurs</h2>
    <form action="reservation" method="get" class="form-inline mb-3">
        <input type="hidden" name="action" value="report" />
        <label class="mr-2" for="filtre">Filtrer par statut</label>
        <select name="filtre" id="filtre" class="form-control mr-2">
            <option value="">Tous</option>
            <option value="paye">Payé</option>
            <option value="avance">Avec avance</option>
            <option value="reste">Reste à payer</option>
        </select>
        <button type="submit" class="btn btn-secondary">Afficher</button>
    </form>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Voiture</th>
            <th>Client</th>
            <th>Place</th>
            <th>Payment</th>
            <th>Montant avance</th>
            <th>Date voyage</th>
        </tr>
        </thead>
        <tbody>
        <% for (Reservation r : reservations) { %>
            <tr>
                <td><%= r.getIdreserv() %></td>
                <td><%= r.getIdvoit() %></td>
                <td><%= r.getIdcli() %></td>
                <td><%= r.getPlace() %></td>
                <td><%= r.getPayment() %></td>
                <td><%= r.getMontant_avance() %></td>
                <td><%= r.getDate_voyage() %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
