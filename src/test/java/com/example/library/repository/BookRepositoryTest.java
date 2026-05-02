package com.example.library.repository;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAllBooksWithAuthors_ReturnsBooksWithAuthorsFetched() {
        // Given
        Author author = new Author("Test Author", "Bio");
        author = authorRepository.save(author);

        Book book = new Book("Test Book", "123456789", author);
        bookRepository.save(book);

        // When
        List<Book> books = bookRepository.findAllBooksWithAuthors();

        // Then
        assertFalse(books.isEmpty());
        assertNotNull(books.get(0).getAuthor());
        assertEquals("Test Author", books.get(0).getAuthor().getName());
    }
}
