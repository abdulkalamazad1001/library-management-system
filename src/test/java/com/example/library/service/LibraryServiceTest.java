package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks_ReturnsListOfBooks() {
        Author author = new Author("Test Author", "Bio");
        Book book1 = new Book("Title 1", "ISBN1", author);
        Book book2 = new Book("Title 2", "ISBN2", author);

        when(bookRepository.findAllBooksWithAuthors()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = libraryService.getAllBooks();

        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAllBooksWithAuthors();
    }

    @Test
    void saveBook_ReturnsSavedBook() {
        Author author = new Author("Test Author", "Bio");
        Book book = new Book("Title", "ISBN", author);

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book savedBook = libraryService.saveBook(book);

        assertNotNull(savedBook);
        assertEquals("Title", savedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void updateBook_UpdatesAndReturnsBook() {
        Author author = new Author("Test Author", "Bio");
        Book existingBook = new Book("Old Title", "Old ISBN", author);
        existingBook.setId(1L);

        Book updatedDetails = new Book("New Title", "New ISBN", author);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(existingBook);

        Book result = libraryService.updateBook(1L, updatedDetails);

        assertEquals("New Title", result.getTitle());
        assertEquals("New ISBN", result.getIsbn());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(existingBook);
    }
}
