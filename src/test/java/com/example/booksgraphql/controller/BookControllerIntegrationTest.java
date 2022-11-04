package com.example.booksgraphql.controller;

import com.example.booksgraphql.model.Book;
import com.example.booksgraphql.repository.AuthorRepository;
import com.example.booksgraphql.repository.BookRepository;
import com.example.booksgraphql.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

@Import({BookRepository.class, AuthorRepository.class, BookService.class})
@GraphQlTest(BookController.class)
class BookControllerIntegrationTest {

  @Autowired
  private GraphQlTester graphQlTester;

  @Test
  void testFindAllBooksShouldReturnAllBooks() {

    // language=GraphQL
    String document = """
     query {
      allBooks {
        id,
        title,
        pages
      }
     }
     """;

    this.graphQlTester.document(document)
        .execute()
        .path("allBooks")
        .entityList(Book.class)
        .hasSize(3);

  }

  @Test
  void validIdShouldReturnBook() {

    // language=GraphQL
    String document = """
    query findOne($id: ID!) {
       findOne(id: $id) {
        id,
        title,
        pages
       }
    }
    """;

    graphQlTester.document(document)
        .variable("id", 1)
        .execute()
        .path("findOne")
        .entity(Book.class)
        .satisfies(book -> {
          Assertions.assertEquals(1, book.id());
          Assertions.assertEquals("The Hobbit", book.title());
        });
  }
}