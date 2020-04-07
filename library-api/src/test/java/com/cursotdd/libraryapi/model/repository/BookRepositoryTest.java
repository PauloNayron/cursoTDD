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

import java.util.Optional;

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
    @DisplayName("Deve Retornar verdadeiro quando existir um livro na Base com o isbn informado.")
    public void returnTrueWhenIsbnExists () {
        // cenario
        String isbn = "123";
        Object book = createNewBook();
        entityManager.persist(book);
        // execucao
        boolean exists = repository.existsByIsbn(isbn);
        // verificacao
        assertThat(exists).isTrue();
    }

    private Book createNewBook() {
        return Book.builder().title("Aventuras").author("Fulano").isbn("123").build();
    }

    @Test
    @DisplayName("Deve Retornar verdadeiro quando existir.")
    public void returnFalseWhenIsbnDoesntExists () {
        // cenario
        String isbn = "123";
        // execucao
        boolean exists = repository.existsByIsbn(isbn);
        // verificacao
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve obter um livro por id.")
    public void findByIdTest () {
        // cenário
        Book book = createNewBook();
        entityManager.persist(book);
        // execução
        Optional<Book> foundBook = repository.findById(book.getId());
        // verificação
        assertThat(foundBook.isPresent()).isTrue();
    }
}