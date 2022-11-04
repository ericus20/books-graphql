package com.example.booksgraphql.repository;

import com.example.booksgraphql.model.Book;
import com.example.booksgraphql.model.Rating;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

  private final AuthorRepository authorRepository;
  private List<Book> books = new ArrayList<>();

  public BookRepository(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public List<Book> findAll() {
    return books;
  }

  public Book findOne(Integer id) {
    return books.stream()
        .filter(book -> Objects.equals(book.id(), id))
        .findFirst().orElseThrow(() -> new RuntimeException("Book not found"));
  }

  @PostConstruct
  private void init() {
    books.add(new Book(1, "The Hobbit", 295, Rating.FOUR_STARS, authorRepository.findByName("Josh Long")));
    books.add(new Book(2, "The Fellowship of the Ring", 423, Rating.FIVE_STARS, authorRepository.findByName("Oliver Gierke")));
    books.add(new Book(3, "The Two Towers", 352, Rating.FIVE_STARS, authorRepository.findByName("Josh Long")));
  }

}
