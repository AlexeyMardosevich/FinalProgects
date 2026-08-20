package online.javaclass.bookstore.data.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponseDto<T> {

    private final List<T> items;
    private final int page;
    private final int pageSize;
    private final int totalItems;
    private final int totalPages;

    public PageResponseDto(List<T> items, int page, int pageSize, int totalItems) {

        this.items = items;
        this.page = page;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = totalItems == 0 ? 0 : (totalItems + pageSize - 1) / pageSize;
    }
}
