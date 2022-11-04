package com.example.booksgraphql.service;

import com.example.booksgraphql.model.Book;
import com.example.booksgraphql.repository.BookRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
  
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public Book findOne(Integer id) {
    return bookRepository.findOne(id);
  }
}
