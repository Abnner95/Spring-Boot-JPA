package com.bookstore.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.jpa.dtos.BookRecordDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.services.BookService;

@RestController 
@RequestMapping("/bookstore/book")
public class BookController {
  
    private final BookService bookService;

    public BookController(com.bookstore.services.BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public <T> ResponseEntity<List<BookModel>> getAllBooks() {
        return (ResponseEntity<List<BookModel>>) ResponseEntity.status(HttpStatus.OK).body((List<BookModel>) bookService.getAllBooks());
    }


    @PostMapping 
    public ResponseEntity<BookModel> saveBook(@RequestBody BookRecordDto bookRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Book deleted successfully");
    }
    }

