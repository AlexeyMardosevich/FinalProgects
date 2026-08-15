package online.javaclass.bookstore.controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;

import java.io.IOException;

@Log4j
public class AuthenticationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String command = req.getParameter("command");
        if (isRequireAuthentication(command)) {
            HttpSession session = req.getSession();
            if (session.getAttribute("user") == null) {
                req.getRequestDispatcher("jsp/error.jsp").forward(req, res);
                return;
            }
        }
        chain.doFilter(req, res);
    }

    private static boolean isRequireAuthentication(String command) {
        return !command.equals("book")
               && !command.equals("books")
               && !command.equals("login")
               && !command.equals("logout");
    }
}
