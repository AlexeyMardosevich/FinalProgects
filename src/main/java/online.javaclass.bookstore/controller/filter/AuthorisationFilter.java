package online.javaclass.bookstore.controller.filter;


import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.service.dto.UserDto;
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
public class AuthorisationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String command = req.getParameter("command");
        HttpSession session = req.getSession();
        if (requireAuthorisation(command)) {
            UserDto userDto = (UserDto) session.getAttribute("user");
            if (!userDto.getRole().equals("admin")) {
                req.getRequestDispatcher("jsp/error.jsp").forward(req, res);
                return;
            }
        }
        chain.doFilter(req, res);
    }

    private static boolean requireAuthorisation(String command) {
        return !command.equals("add_book");
    }
}
