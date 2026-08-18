package online.javaclass.bookstore.controller.filter;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Log4j2
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
