package utils;

import model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication {
       public static boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (boolean)session.getAttribute("isAuthenticated");
        }
        return false;
    }
    public static boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (boolean)session.getAttribute("isAuthenticated") && "admin".equals(session.getAttribute("role"));
        }
        return false;
    }
}