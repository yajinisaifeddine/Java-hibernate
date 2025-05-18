package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Don't create if it doesn't exist
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
        
        // Redirect to login page after logout
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
