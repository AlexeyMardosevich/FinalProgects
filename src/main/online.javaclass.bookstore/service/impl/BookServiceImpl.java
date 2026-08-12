package service.impl;

import data.dto.PageableDto;
import data.entities.Book;
import data.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import mapper.ServiceDtoMapper;
import service.BookService;
import service.dto.BookDto;
import service.exception.AppException;

import java.util.List;

@Log4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ServiceDtoMapper serviceDtoMapper;

    @Override
    public int countAll() {
        return bookRepository.countAll();
    }

    @Override
    public BookDto find(Long id) {
        BookDto bookDto = bookRepository.find(id);
        if (bookDto == null) {
            throw new AppException("Couldn't find book with id:" + id);
        }
        return toDto(bookDto);
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.getAll().stream().
                map(serviceDtoMapper::toDto).
                toList();
    }
    public List<BookDto> getAll(PageableDto pageableDto) {
        List<BookDto> books = bookRepository.getAll(pageableDto.getPageSize(), pageableDto.getOffset()).stream().
                map(serviceDtoMapper::toDto).
                toList();
        int countAll = bookRepository.countAll();
        int pages = countAll / pageableDto.getPageSize();
        if (countAll % pageableDto.getPageSize() != 0) {
            pages++;
        }
        pageableDto.setTotalItems(countAll);
        pageableDto.setTotalPages(pages);

        return books;
    }


    @Override
    public BookDto create(BookDto bookDto) {
        Book book = toEntity(bookDto);
        //должна быть валидация (проверка логина, пароля и т.д)
        Book created = bookRepository.create(book);
        return toDto(created);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Book book = toEntity(bookDto);
        Book update = bookRepository.update(book);
        return toDto(update);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean delete = bookRepository.deleteById(id);
        if (!delete) {
            throw new AppException("Couldn't delete book with id: " + id);
        }
        return delete;
    }

    private BookDto toDto(Book entity) {
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAuthor(entity.getAuthor());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    private Book toEntity(BookDto dto) {
        Book entity = new Book();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}
