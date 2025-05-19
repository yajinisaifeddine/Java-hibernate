package servlet;

import dao.UserDao;
import model.User;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        Optional<User> dbUser = userDao.findBy("username", username);
        if (dbUser.isPresent()) {
            if (dbUser.get().getPassword().equals(password)) {
                session.setAttribute("isAuthenticated", true);
                session.setAttribute("role", dbUser.get().getRole());
                response.sendRedirect(request.getContextPath() + "/list");
            }
        } else {

            request.setAttribute("error", "Invalid credentials");
            session.setAttribute("isAuthenticated", false);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}