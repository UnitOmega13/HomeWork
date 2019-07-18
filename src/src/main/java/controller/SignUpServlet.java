package controller;

import factories.UserServiceFactory;
import model.User;
import service.UserService;
import utils.IdGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/registration")
public class SignUpServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static Long id = IdGenerator.generateId();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String accessRole = req.getParameter("accessRole");
        String repeatedPassword = req.getParameter("repeatedPassword");
        if (email.isEmpty() || login.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Empty fields!");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        } else if (password.equals(repeatedPassword)) {
            userService.add(new User(id, email, login, password, accessRole));
            resp.setStatus(HttpServletResponse.SC_OK);
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Passwords are not same!");
            req.setAttribute("defaultLogin", login);
            req.setAttribute("defaultEmail", email);
            req.setAttribute("accessRole", accessRole);
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }
}
