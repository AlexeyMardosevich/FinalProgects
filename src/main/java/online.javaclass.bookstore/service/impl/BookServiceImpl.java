package online.javaclass.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.entities.Book;
import online.javaclass.bookstore.data.repository.BookRepository;
import online.javaclass.bookstore.mapper.ServiceDtoMapper;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import online.javaclass.bookstore.service.exception.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toDto;
import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toEntity;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookDto find(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new AppException("Couldn't find book with id:" + id));
        return toDto(book);
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll()
                .stream().
                map(ServiceDtoMapper::toDto).
                collect(Collectors.toList());
    }

    @Override
    public Page<BookDto> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(ServiceDtoMapper::toDto);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        if (bookDto == null) {
            throw new AppException("Book must not be null");
        }
        if (bookDto.getName() == null || bookDto.getName().isBlank()) {
            throw new AppException("Name must not be blank");
        }
        if (bookDto.getAuthor() == null || bookDto.getAuthor().isBlank()) {
            throw new AppException("Author must not be blank");
        }
        if (bookDto.getPrice() == null) {
            throw new AppException("Price must be greater than zero");
        }
        Book book = toEntity(bookDto);
        Book created = bookRepository.save(book);
        return toDto(created);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        if (bookDto == null || bookDto.getId() == null) {
            throw new AppException("Book id must not be null");
        }

        Book book = bookRepository.findById(bookDto.getId())
                .orElseThrow(() -> new AppException("Couldn't find book with id: " + bookDto.getId()));

        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());

        Book updated = bookRepository.save(book);
        return toDto(updated);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null) {
            throw new AppException("Book id must not be null");
        }
        if (!bookRepository.existsById(id)) {
            throw new AppException("Couldn't find book with id: " + id);
        }
        bookRepository.deleteById(id);
        return true;
    }
}
