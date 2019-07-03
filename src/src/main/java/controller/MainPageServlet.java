package controller;

import dao.ProductDAO;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageServlet extends HttpServlet {
    private static final AccountService ACCOUNT_SERVICE = AccountService.instance();
    private static final ProductDAO PRODUCT_DAO = ProductDAO.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ACCOUNT_SERVICE.isLogin()) {
            req.setAttribute("userName", ACCOUNT_SERVICE.getUser().getLogin());
            req.setAttribute("products",PRODUCT_DAO.getAll());
            req.getServletContext().getRequestDispatcher("/MainPage.jsp").forward(req, resp);

        } else {
            req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
