package online.javaclass.bookstore.data.dto;


import lombok.Getter;
import lombok.Setter;

public class PageableDto {
    @Getter
    private final int page;
    @Getter
    private final int pageSize;
    @Getter
    private final int offset;
    @Getter
    @Setter
    private int totalItems;
    @Getter
    @Setter
    private int totalPages;

    public PageableDto(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
        this.offset = calculateOffset();
    }

    private int calculateOffset() {
        return pageSize * (page - 1);
    }

    private int calculateLimit() {
        return pageSize;
    }
}
