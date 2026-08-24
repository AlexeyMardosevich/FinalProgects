package online.javaclass.bookstore.web.interception;

import lombok.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, @NonNull HttpServletResponse resp, @NonNull Object handler) throws Exception {
        System.out.printf("Interceptor PRE: %s, method: %s%n", req.getRequestURI(), req.getMethod());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, @NonNull HttpServletResponse resp, @NonNull Object handler, ModelAndView modelAndView) throws Exception {
        System.out.printf("Interceptor POST: %s, method: %s%n", req.getRequestURI(), req.getMethod());
        HandlerInterceptor.super.postHandle(req, resp, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest req, @NonNull HttpServletResponse resp, @NonNull Object handler, Exception ex) throws Exception {
        System.out.printf("Interceptor AFTER: %s, method: %s%n", req.getRequestURI(), req.getMethod());
        HandlerInterceptor.super.afterCompletion(req, resp, handler, ex);
    }
}
