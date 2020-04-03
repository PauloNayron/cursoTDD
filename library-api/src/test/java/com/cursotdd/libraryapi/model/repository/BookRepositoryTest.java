package com.cursotdd.libraryapi.model.repository;

import com.cursotdd.libraryapi.api.model.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("Test")
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName("Deve Retornar verdadeiro quando existir")
    public void returnTrueWhenIsbnExists () {
        // cenario
        String isbn = "123";
        Object book = Book.builder().title("Aventuras").author("Fulano").isbn(isbn).build();
        entityManager.persist(book);
        // execucao
        boolean exists = repository.existsByIsbn(isbn);
        // verificacao
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve Retornar verdadeiro quando existir")
    public void returnFalseWhenIsbnDoesntExists () {
        // cenario
        String isbn = "123";
        // execucao
        boolean exists = repository.existsByIsbn(isbn);
        // verificacao
        assertThat(exists).isFalse();
    }
}