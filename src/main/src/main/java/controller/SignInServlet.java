package controller;

import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    private static final AccountService ACCOUNT_SERVICE = AccountService.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ACCOUNT_SERVICE.isLogin()) {
            resp.sendRedirect("/index");
            return;
        }
        req.setAttribute("process", "Sign in");
        req.setAttribute("action", "Login");
        req.getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (ACCOUNT_SERVICE.signIn(login, password)) {
            resp.sendRedirect("/MainPage");
        } else {
            req.setAttribute("isInvalid", "Invalid login or pass");
            req.setAttribute("process", "Sign In");
            req.setAttribute("action", "Login");
            req.getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
        }
    }
}
