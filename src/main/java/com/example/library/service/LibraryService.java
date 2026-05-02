package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import java.util.List;

public interface LibraryService {
    List<Book> getAllBooks();
    Book saveBook(Book book);
    Book getBookById(Long id);
    Book updateBook(Long id, Book book);
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
}
