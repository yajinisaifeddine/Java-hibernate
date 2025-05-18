package servlet;
import dao.DaoFactory;
import model.Product;
import utils.Authentication;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Admin Authentication
        if (!Authentication.isAdmin(request)) {
            response.sendRedirect("login.jsp?error=unauthorised");
            return;
        }

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addProduct(request, response);
                    break;
                case "edit":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Admin Authentication
        if (!Authentication.isAdmin(request)) {
            response.sendRedirect("login.jsp?error=unauthorised");
            return;
        }

        String action = request.getParameter("action");

        try {
            if (action == null) {
                // For actions like add, edit, delete
                listProducts(request, response);  // Admin only
            } else {
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    default:
                        listProducts(request, response);  // Admin only
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products", DaoFactory.getProductDao().findAll());
        request.getRequestDispatcher("/WEB-INF/views/list-products.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/add-product.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));


        Optional<Product> optionalProduct = DaoFactory.getProductDao().findById(id);
        if (optionalProduct.isEmpty())return;

        request.setAttribute("product", optionalProduct.get());
        request.getRequestDispatcher("/WEB-INF/views/edit-product.jsp").forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        Product newProduct = new Product(name, description, price);
        DaoFactory.getProductDao().save(newProduct);
        response.sendRedirect("products");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product(name, description, price);
        product.setId(id);
        DaoFactory.getProductDao().update(product);
        response.sendRedirect("products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        DaoFactory.getProductDao().delete(id);
        response.sendRedirect("products");
    }
}
