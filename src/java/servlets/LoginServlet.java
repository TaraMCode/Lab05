package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //If the parameter “logout” exists, 
        // invalidate the session and display a message that the user has successfully logged out
        String action = request.getParameter("logout"); // gets the query string from login.jsp
        if (action != null) { // if the logout button is clicked
            HttpSession session = request.getSession(); // get the session
            session.invalidate(); // invalidate the session
            request.setAttribute("message", "You have successfully logged out."); // return a message informing the user they've logged out
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_storage");

        if (user != null) {
            response.sendRedirect("home");
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usernameString = request.getParameter("person_username");
        String passwordString = request.getParameter("person_password");

        // doPost() first validates that user name and password are not empty. 
        if (usernameString == null || usernameString.equals("") || passwordString == null || passwordString.equals("")) {
            // response.sendRedirect("Hello");
            request.setAttribute("message", "Invalid login.");
            request.setAttribute("username_attribute", usernameString); // reloads username to boxes
            request.setAttribute("password_attribute", passwordString); // reloads password to boxes
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        // Then, it passes the user name and password parameters to the login() method of a service class called AccountService.
        // if (login() returns a non-null value,)
        // store the username in a session variable and redirect(not forward) the user to the home url.

        // If the authentication parameters are invalid, display an appropriate error message, keeping the textboxes filled in with what the user had 
        // previously entered and forward the user to login.js
        // if (authentication parameters are invalid) {
        //request.setAttribute("message", "Invalid login.");
        AccountService userService = new AccountService();
        User user = userService.login(usernameString, passwordString);

        if (user == null) {
            request.setAttribute("message", "Invalid login.");
            request.setAttribute("username_attribute", usernameString); // reloads username to boxes
            request.setAttribute("password_attribute", passwordString); // reloads password to boxes
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user_storage", user);
            response.sendRedirect("home");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
