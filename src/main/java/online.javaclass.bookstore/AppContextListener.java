package online.javaclass.bookstore;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    private static AnnotationConfigApplicationContext context;

    public static AnnotationConfigApplicationContext getContext() {
        return context;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        context.close();
    }
}
