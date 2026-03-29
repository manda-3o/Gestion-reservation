<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Client Management</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <h1>Client List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Phone Number</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<User> users = (List<User>) request.getAttribute("userList");
                if (users != null) {
                    for (User user : users) {
            %>
            <tr>
                <td><%= user.getIdcli() %></td>
                <td><%= user.getNom() %></td>
                <td><%= user.getNumtel() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="3">No clients found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <a href="<%= request.getContextPath() %>/jsp/index.jsp">Back to Home</a>
</body>
</html>