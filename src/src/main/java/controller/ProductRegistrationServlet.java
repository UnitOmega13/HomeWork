package controller;

import dao.ProductDAO;
import Entiny.Product;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductRegistrationServlet extends HttpServlet {
    private static final AccountService ACCOUNT_SERVICE = AccountService.instance();
    private static final ProductDAO PRODUCT_DAO = ProductDAO.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ACCOUNT_SERVICE.getUser().getLogin().equals("admin")) {
            resp.sendRedirect("/pokupka");
            return;
        }
        req.getServletContext().getRequestDispatcher("/AddItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String imgLink = req.getParameter("img");
        double price = -1;
        if (!req.getParameter("price").isEmpty()) {
            price = Double.valueOf(req.getParameter("price"));
        }
        if (name.isEmpty() || imgLink.isEmpty() || price < 0) {
            req.setAttribute("isEmpty", "All fields must be initialized and price must be biggest than 0.");
            req.getServletContext().getRequestDispatcher("/AddItem.jsp").forward(req, resp);
        } else {
            PRODUCT_DAO.add(new Product(name, imgLink, price));
            resp.sendRedirect("/pokupka");
        }
    }
}
