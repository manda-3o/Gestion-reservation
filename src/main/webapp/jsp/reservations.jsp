<jsp:root xmlns:jstl="http://java.sun.com/jsp/jstl/core" version="2.0" xmlns:jsp="http://java.sun.com/JSP/Page">
    <jsp:directive.page contentType="text/html;charset=UTF-8" language="java" />
    <jsp:directive.include file="../WEB-INF/header.jsp" />

    <html>
    <head>
        <title>Make a Reservation</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css" />
    </head>
    <body>
        <h1>Make a Reservation</h1>
        <form action="${pageContext.request.contextPath}/reservations" method="post">
            <label for="vehicle">Select Vehicle:</label>
            <select name="vehicle" id="vehicle" required>
                <c:forEach var="vehicle" items="${vehicles}">
                    <option value="${vehicle.idvoit}">${vehicle.Design} - ${vehicle.type}</option>
                </c:forEach>
            </select>
            <br/>

            <label for="client">Select Client:</label>
            <select name="client" id="client" required>
                <c:forEach var="client" items="${clients}">
                    <option value="${client.idcli}">${client.nom}</option>
                </c:forEach>
            </select>
            <br/>

            <label for="place">Select Place:</label>
            <input type="number" name="place" id="place" required />
            <br/>

            <label for="dateVoyage">Date of Travel:</label>
            <input type="date" name="dateVoyage" id="dateVoyage" required />
            <br/>

            <label for="payment">Payment Method:</label>
            <select name="payment" id="payment" required>
                <option value="sans avance">Sans Avance</option>
                <option value="avec avance">Avec Avance</option>
                <option value="tout payé">Tout Payé</option>
            </select>
            <br/>

            <label for="montantAvance">Montant Avance:</label>
            <input type="number" name="montantAvance" id="montantAvance" />
            <br/>

            <input type="submit" value="Reserve" />
        </form>

        <jsp:directive.include file="../WEB-INF/footer.jsp" />
    </body>
    </html>
</jsp:root>