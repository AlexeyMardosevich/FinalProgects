package controller;

import controller.command.Command;
import controller.command.CommandFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebServlet("/controller")
public class FrontController extends HttpServlet {
    private static final Logger log = LogManager.getLogger(FrontController.class);

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
        Command command = CommandFactory.INSTANCE.getController(commandParam);
        String page;
        try {
            page = command.execute(req);
        }catch (Exception e){
            page = processError(req, e);
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    private String processError(HttpServletRequest req, Exception e) {
        String page;
        page = CommandFactory.INSTANCE.getController("error").execute(req);
        String massage = e.getMessage();
        req.setAttribute("massage", massage);
        return page;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("Initialized");
    }

    @Override
    public void destroy() {
        log.info("Destroy");
    }
}
