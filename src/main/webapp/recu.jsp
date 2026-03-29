<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.transportation.model.Reservation, com.transportation.model.Client, com.transportation.model.Voiture" %>
<%@ include file="header.jsp" %>
<%
    Reservation reservation = (Reservation) request.getAttribute("reservation");
    Client client = (Client) request.getAttribute("client");
    Voiture voiture = (Voiture) request.getAttribute("voiture");
    Integer reste = (Integer) request.getAttribute("reste");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reçu de réservation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
</head>
<body>
<div class="container mt-4">
    <h2>Reçu de réservation</h2>
    <div class="card">
        <div class="card-body">
            <p><strong>Référence réservation:</strong> <%= reservation.getIdreserv() %></p>
            <p><strong>Client:</strong> <%= client.getNom() %> (<%= client.getNumtel() %>)</p>
            <p><strong>Voiture:</strong> <%= voiture != null ? voiture.getDesign() : "N/A" %> (<%= reservation.getIdvoit() %>)</p>
            <p><strong>Type voiture:</strong> <%= voiture != null ? voiture.getType() : "N/A" %></p>
            <p><strong>Place:</strong> <%= reservation.getPlace() %></p>
            <p><strong>Date réservation:</strong> <%= reservation.getDate_reserv() %></p>
            <p><strong>Date voyage:</strong> <%= reservation.getDate_voyage() %></p>
            <p><strong>Montant avance:</strong> <%= reservation.getMontant_avance() %> dh</p>
            <p><strong>Frais total:</strong> <%= voiture != null ? voiture.getFrais() : 0 %> dh</p>
            <p><strong>Reste à payer:</strong> <%= (voiture != null && reservation != null) ? reservation.calculerReste(voiture.getFrais()) : 0 %> dh</p>
            <p><strong>Mode payment:</strong> <%= reservation.getPayment() %></p>
        </div>
    </div>
    <a href="reservations.jsp" class="btn btn-primary mt-3">Retour à la liste</a>
</div>
</body>
</html>
