package online.javaclass.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.dto.PageResponseDto;
import online.javaclass.bookstore.data.dto.PageableDto;
import online.javaclass.bookstore.data.entities.Book;
import online.javaclass.bookstore.data.repository.BookRepository;
import online.javaclass.bookstore.mapper.ServiceDtoMapper;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import online.javaclass.bookstore.service.exception.AppException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toDto;
import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toEntity;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public int countAll() {
        return bookRepository.countAll();
    }

    @Override
    public BookDto find(Long id) {
        Book book = bookRepository.find(id);

        if (isNull(book)) {
            throw new AppException("Couldn't find book with id:" + id);
        }

        return toDto(book);
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.getAll().stream().
                map(ServiceDtoMapper::toDto).
                collect(Collectors.toList());
    }

    public PageResponseDto<BookDto> getAll(PageableDto pageableDto) {
        List<BookDto> books = bookRepository
                .getAll(
                        pageableDto.getPageSize(),
                        pageableDto.getOffset()
                )
                .stream()
                .map(ServiceDtoMapper::toDto)
                .collect(Collectors.toList());
        int totalItems = bookRepository.countAll();
        return new PageResponseDto<>(books, pageableDto.getPage(), pageableDto.getPageSize(), totalItems);
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
        return bookRepository.deleteById(id);
    }
}
