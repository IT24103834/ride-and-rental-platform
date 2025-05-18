<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Bike</title>
</head>
<body>
<h2>Delete Bike</h2>
<form action="bikes" method="post">
    Model to Delete: <input type="text" name="model"><br><br>
    <input type="hidden" name="action" value="delete">
    <input type="submit" value="Delete Bike">
</form>
<br>
<a href="bikes">Back to List</a>
</body>
</html>
