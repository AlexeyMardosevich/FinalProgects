package online.javaclass.bookstore.data.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
public class PageableDto {
    private final int page;
    private final int pageSize;
    private final int offset;

    @Setter
    private int totalItems;

    @Setter
    private int totalPages;

    public PageableDto(int page, int pageSize) {
        if (page < 1) {
            throw new IllegalArgumentException("Page must be greater than or equal to 1");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must be greater than zero");
        }
        this.page = page;
        this.pageSize = pageSize;
        this.offset = Math.multiplyExact(page - 1, pageSize);
    }

    public void calculatePages(int totalItems) {
        this.totalItems = totalItems;
        this.totalPages = totalItems == 0
                ? 0
                : (totalItems + pageSize - 1) / pageSize;
    }
}
