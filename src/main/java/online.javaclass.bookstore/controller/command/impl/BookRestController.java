package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import online.javaclass.bookstore.service.exception.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public BookDto get(@PathVariable Long id) {
        return bookService.find(id);
    }

    @GetMapping()
    public Page<BookDto> getAll(Pageable pageable) {
        return bookService.getAll(pageable);
    }

    @PostMapping()
    public ResponseEntity<BookDto> create(@RequestBody @Valid BookDto bookDto, Errors errors) {
        checkErrors(errors);
        BookDto create = bookService.create(bookDto);
        return buildResponseCreated(create);
    }

    @PostMapping("/{id}")
    public BookDto update(@PathVariable Long id, @RequestBody @Valid BookDto bookDto, Errors errors) {
        checkErrors(errors);
        bookDto.setId(id);
        return bookService.update(bookDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookDto updatePart(@PathVariable Long id, @RequestBody @Valid BookDto bookDto) {
        bookDto.setId(id);
        return bookService.update(bookDto);
    }


    private void checkErrors(Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    private ResponseEntity<BookDto> buildResponseCreated(BookDto create) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(getLocation(create))
                .body(create);
    }

    private URI getLocation(BookDto bookDto) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookDto.getId())
                .toUri();
    }
}
