package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        
        boolean isLoggedInAdmin = (session != null) && (session.getAttribute("isAuthenticated") != null) && ((Boolean) session.getAttribute("isAuthenticated")) && (session.getAttribute("role")).equals("admin");

        if (!isLoggedInAdmin) {
            // Not logged in: redirect to login page
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            // User is logged in: continue normally
            chain.doFilter(request, response);
        }
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
