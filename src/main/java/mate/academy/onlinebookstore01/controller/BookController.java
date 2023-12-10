package mate.academy.onlinebookstore01.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore01.dto.BookDto;
import mate.academy.onlinebookstore01.dto.CreateBookRequestDto;
import mate.academy.onlinebookstore01.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/api/books/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public BookDto createBook(@RequestBody CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }
}
