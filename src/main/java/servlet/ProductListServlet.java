package servlet;
import dao.ProductDao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/list")
public class ProductListServlet extends HttpServlet {


    private ProductDao productDao;
    @Override
    public void init() throws ServletException {

        super.init();
        productDao = new ProductDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // List all products (No authentication check)
        try {
            listProducts(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        
        // Fetch all products and forward to the view
        request.setAttribute("products", productDao.findAll());
        request.setAttribute("role",session.getAttribute("role"));
        
        boolean isLoggedIn = ((session.getAttribute("isAuthenticated") !=null && (boolean) session.getAttribute("isAuthenticated"))) ;
        
        request.setAttribute("isAuthenticated",isLoggedIn);
        request.getRequestDispatcher("/WEB-INF/views/list-products.jsp").forward(request, response);
    }
}