package com.example.booksgraphql.controller;

import com.example.booksgraphql.model.Book;
import com.example.booksgraphql.service.BookService;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @QueryMapping
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<Book> allBooks() {
    return bookService.findAll();
  }

  @QueryMapping
//  @Secured("ROLE_ADMIN")
  public Book findOne(@Argument Integer id) {
    return bookService.findOne(id);
  }
}


