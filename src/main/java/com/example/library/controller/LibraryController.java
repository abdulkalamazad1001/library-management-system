package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = libraryService.getAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/new")
    public String showFormForAdd(Model model) {
        Book book = new Book();
        List<Author> authors = libraryService.getAllAuthors();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book, Model model) {
        try {
            libraryService.saveBook(book);
            return "redirect:/books";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Error: A book with this ISBN already exists.");
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while saving the book.");
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "form";
        }
    }

    @GetMapping("/edit/{id}")
    public String showFormForUpdate(@PathVariable("id") Long id, Model model) {
        Book book = libraryService.getBookById(id);
        List<Author> authors = libraryService.getAllAuthors();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "update-form";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book book, Model model) {
        try {
            libraryService.updateBook(id, book);
            return "redirect:/books";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Error: A book with this ISBN already exists.");
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "update-form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while updating the book.");
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "update-form";
        }
    }
}
