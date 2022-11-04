package com.example.booksgraphql.repository;

import com.example.booksgraphql.model.Author;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository {

  public static final String AUTHOR_NOT_FOUND = "Author not found";

  private final List<Author> authors = new ArrayList<>();

  public List<Author> findAll() {
    return authors;
  }

  public Author findById(Integer id) {
    return authors.stream()
        .filter(author -> author.id().equals(id))
        .findFirst().orElseThrow(() -> new RuntimeException(AUTHOR_NOT_FOUND));
  }

  public Author findByName(String name) {
    return authors.stream()
        .filter(author -> author.fullName().equals(name))
        .findFirst().orElseThrow(() -> new RuntimeException(AUTHOR_NOT_FOUND));
  }

  @PostConstruct
  private void init() {
    authors.add(new Author(1, "Josh", "Long"));
    authors.add(new Author(2, "Oliver", "Gierke"));
    authors.add(new Author(3, "Stephan", "Hinz"));
  }

}
