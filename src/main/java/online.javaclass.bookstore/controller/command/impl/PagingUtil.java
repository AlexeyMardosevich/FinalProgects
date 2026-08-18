package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.dto.PageableDto;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;


@Controller
@Log4j2
@RequiredArgsConstructor
public class PagingUtil {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_PAGE_SIZE = 5;

    public static PageableDto getPageable(HttpServletRequest req) {
        return new PageableDto(getPage(req), getPageSize(req));
    }

    private static int getPageSize(HttpServletRequest req) {
        return extractParam(req, DEFAULT_PAGE_SIZE, "page_size");
    }

    private static int getPage(HttpServletRequest req) {
        return extractParam(req, DEFAULT_PAGE, "page");
    }

    private static int extractParam(HttpServletRequest req, int defaultValue, String paramName) {
        String page = req.getParameter(paramName);
        if (page == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(page);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}