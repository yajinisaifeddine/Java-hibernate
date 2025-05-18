package servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import model.User;

 @WebServlet("/register")
public class RegisterServlet  extends HttpServlet{

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user=  new User(username,password);
		if(DaoFactory.getUserDao().findBy("username",username).isPresent()){
			response.sendRedirect(request.getContextPath() + "/signup.jsp?error=failed");
			return;
		}
        Optional<User> dbUser = DaoFactory.getUserDao().save(user);
        System.out.println(dbUser.get());
        if(dbUser.isPresent()) {
        	HttpSession session = request.getSession();
        	session.setAttribute("isAuthenticated",true);
        	session.setAttribute("role","user");
        	response.sendRedirect(request.getContextPath() + "/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/signup.jsp?error=failed");
        }
        
    }

}
