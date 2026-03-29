<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.transportation.dao.VehicleDAO, com.transportation.db.DatabaseConnection, com.transportation.model.Voiture" %>
<%@ page import="java.util.List" %>
<%
    List<Voiture> voitures;
    try (java.sql.Connection conn = DatabaseConnection.getConnection()) {
        voitures = new VehicleDAO(conn).listAll();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Réservation</title>
    <link rel="stylesheet" href="resources/css/style.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container mt-4">
    <h2>Nouvelle réservation</h2>
    <form method="post" action="reservation" id="reservationForm">
        <div class="form-group">
            <label for="idreserv">ID réservation</label>
            <input type="text" class="form-control" id="idreserv" name="idreserv" required>
        </div>
        <div class="form-group">
            <label for="idvoit">Voiture</label>
            <select id="idvoit" name="idvoit" class="form-control" required>
                <option value="">-- Choisir --</option>
                <% for (Voiture v : voitures) { %>
                    <option value="<%= v.getIdvoit() %>" data-frais="<%= v.getFrais() %>"><%= v.getDesign() %> - <%= v.getType() %></option>
                <% } %>
            </select>
        </div>
        <div class="form-group">
            <label for="place">Place disponible</label>
            <select id="place" name="place" class="form-control" required>
                <option value="">Choisir la voiture d'abord</option>
            </select>
        </div>
        <div class="form-group">
            <label for="nom">Nom client</label>
            <input type="text" class="form-control" id="nom" name="nom" required>
        </div>
        <div class="form-group">
            <label for="numtel">Téléphone</label>
            <input type="text" class="form-control" id="numtel" name="numtel" required>
        </div>
        <div class="form-group">
            <label for="date_reserv">Date réservation (AAAA-MM-JJ HH:MM:SS)</label>
            <input type="datetime-local" class="form-control" id="date_reserv" name="date_reserv" required>
        </div>
        <div class="form-group">
            <label for="date_voyage">Date voyage</label>
            <input type="date" class="form-control" id="date_voyage" name="date_voyage" required>
        </div>
        <div class="form-group">
            <label for="payment">Mode de paiement</label>
            <select id="payment" name="payment" class="form-control" required>
                <option value="sans avance">sans avance</option>
                <option value="avec avance">avec avance</option>
                <option value="tout payé">tout payé</option>
            </select>
        </div>
        <div class="form-group">
            <label for="montant_avance">Montant avance</label>
            <input type="number" class="form-control" id="montant_avance" name="montant_avance" value="0" min="0" required>
        </div>
        <div class="form-group">
            <label for="reste">Reste à payer</label>
            <input type="text" class="form-control" id="reste" readonly>
        </div>
        <button type="submit" class="btn btn-primary">Valider</button>
    </form>
</div>

<script>
    function refreshPlaces() {
        var idvoit = $('#idvoit').val();
        if (!idvoit) { $('#place').html('<option value="">Choisir la voiture d\'abord</option>'); return; }

        $.getJSON('places-disponibles', {idvoit: idvoit}, function(data) {
            var options = '<option value="">-- Choisir place --</option>';
            $.each(data, function(i, place) {
                options += '<option value="' + place.place + '">' + place.place + '</option>';
            });
            $('#place').html(options);
            updateReste();
        });
    }

    function updateReste() {
        var frais = parseInt($('#idvoit option:selected').data('frais')) || 0;
        var avance = parseInt($('#montant_avance').val()) || 0;
        var reste = frais - avance;
        if (reste < 0) reste = 0;
        $('#reste').val(reste);
    }

    $('#idvoit').change(function() {
        refreshPlaces();
        updateReste();
    });

    $('#montant_avance').on('input', updateReste);
</script>
</body>
</html>
