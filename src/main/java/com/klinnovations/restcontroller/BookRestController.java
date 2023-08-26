package com.klinnovations.restcontroller;
	

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.klinnovations.entity.Book;
import com.klinnovations.repository.BookRepository;


@RestController
@RequestMapping("/api/v1")
public class BookRestController {

    @Autowired
    private BookRepository repo;

    @GetMapping("books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = repo.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Optional<Book> bookOptional = repo.findById(id);
        return bookOptional.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addbook")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = repo.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        book.setBookId(id);
        Book updatedBook = repo.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
