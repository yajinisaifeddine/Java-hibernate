package filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String contextPath = req.getContextPath(); // e.g., /TP
        String path = req.getRequestURI().substring(contextPath.length()); // Strip context

        
     
        if (path.matches("^/(login|register)(\\.jsp)?$") || "/list".equals(path)) {
            chain.doFilter(request, response);
            return;
        }

        boolean isLoggedIn = (session != null) && Boolean.TRUE.equals(session.getAttribute("isAuthenticated"));

        if (!isLoggedIn) {
            res.sendRedirect(contextPath + "/login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
