package online.javaclass.bookstore.controller;


import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.AppContextListener;
import online.javaclass.bookstore.controller.command.Command;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("/controller")
public class FrontController extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("Initialized");
    }

    @Override
    public void destroy() {
        log.info("Destroy");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandParam = req.getParameter("command");
        Command command = AppContextListener.getContext().getBean(commandParam, Command.class);
        String page;
        try {
            page = command.execute(req);
        } catch (Exception e) {
            page = processError(req, e);
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    private String processError(HttpServletRequest req, Exception e) {
        String page;
        page = AppContextListener.getContext().getBean("error").toString();
        String massage = e.getMessage();
        req.setAttribute("massage", massage);
        return page;
    }
}
