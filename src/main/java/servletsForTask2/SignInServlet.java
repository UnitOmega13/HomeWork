package servletsForTask2;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
	private final AccountService accountService;
	public SignInServlet(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	@Override
	protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {
		String login = servletRequest.getParameter("login");
		String password = servletRequest.getParameter("password");
		servletResponse.setContentType("text/html;charset=utf-8");
		UserProfile userByLogin = accountService.getUserByLogin(login);
		if (userByLogin != null) {
			if (userByLogin.getLogin().equals(login) && userByLogin.getPass().equals(password)) {
				servletResponse.setStatus(HttpServletResponse.SC_OK);
				servletResponse.getWriter().print("Authorized: " + login);
			} else {
				servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				servletResponse.getWriter().print("Unauthorized");
			}
		}

	}
}
