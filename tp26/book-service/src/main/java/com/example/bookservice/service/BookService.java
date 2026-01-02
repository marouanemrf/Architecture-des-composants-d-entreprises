package com.example.bookservice.service;

import com.example.bookservice.domain.Book;
import com.example.bookservice.repo.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service de gestion des livres
 */
@Service
public class BookService {
    private final BookRepository repo;
    private final PricingClient pricing;

    public BookService(BookRepository repo, PricingClient pricing) {
        this.repo = repo;
        this.pricing = pricing;
    }

    public List<Book> all() {
        return repo.findAll();
    }

    public Book create(Book b) {
        repo.findByTitle(b.getTitle()).ifPresent(x -> {
            throw new IllegalArgumentException("Titre existant");
        });
        return repo.save(b);
    }

    @Transactional
    public BorrowResult borrow(long id) {
        Book book = repo.findByIdForUpdate(id).orElseThrow(() -> new IllegalArgumentException("Non trouvé"));
        book.decrementStock();
        double price = pricing.getPrice(id);
        return new BorrowResult(book.getId(), book.getTitle(), book.getStock(), price);
    }

    public record BorrowResult(Long id, String title, int stockLeft, double price) {
    }
}
