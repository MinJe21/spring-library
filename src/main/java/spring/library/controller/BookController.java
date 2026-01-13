package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.BookDto;
import spring.library.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @PostMapping()
    public Long create(@RequestBody BookDto.CreateReqDto req) {
        return bookService.create(req);
    }

    @GetMapping()
    public List<BookDto.DetailResDto> getAll(){
        return bookService.getAll();
    }

    @PutMapping("/{bookId}")
    public void update(@PathVariable Long bookId, BookDto.UpdateReqDto req) {
        bookService.update(bookId, req);
    }

    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable Long bookId) {
        bookService.delete(bookId);
    }
}
