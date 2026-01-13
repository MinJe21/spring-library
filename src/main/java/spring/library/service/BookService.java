package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.dto.BookDto;
import spring.library.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Long create(BookDto.CreateReqDto req) {
        Book book = Book.builder()
                .title(req.getTitle())
                .author(req.getAuthor())
                .publisher(req.getPublisher())
                .publicationYear(req.getPublicationYear())
                .classification(req.getClassification())
                .status(req.getStatus())
                .amount(req.getAmount())
                .build();

        return bookRepository.save(book).getId();
    }

    public List<BookDto.DetailResDto> getAll(){
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookDto.DetailResDto::from).toList();
    }

    @Transactional
    public void update(Long id, BookDto.UpdateReqDto req){
        Book b = bookRepository.findById(id).orElseThrow();
        b.setTitle(req.getTitle());
        b.setAuthor(req.getAuthor());
        b.setPublicationYear(req.getPublicationYear());
        b.setClassification(req.getClassification());
        b.setStatus(req.getStatus());
        b.setAmount(req.getAmount());
        bookRepository.save(b);
    }

    public void delete(Long id){
        Book b = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(b);
    }
}
