package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        // If the user attempts to access /home directly and the session object username does not exist, redirect them to /login
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_storage");
        
        if(user == null) {
            response.sendRedirect("login");
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response); // always load JSPs last
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // no code needed here

    }
}
