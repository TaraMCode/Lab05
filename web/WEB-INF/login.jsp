<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post" action="login">
            <label>Username:</label>
            <input type="text" name="person_username" value="${username_attribute}">
            <br>
            <label>Password:</label>
            <input type="text" name="person_password" value="${password_attribute}">
            <br>
            <input type="submit" value="Log in">
        </form>
        <p>${message}</p>
    </body>
</html>
