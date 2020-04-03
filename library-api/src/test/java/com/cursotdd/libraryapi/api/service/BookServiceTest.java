package com.cursotdd.libraryapi.api.service;

import com.cursotdd.libraryapi.api.model.entity.Book;
import com.cursotdd.libraryapi.api.service.impl.BookServiceImpl;
import com.cursotdd.libraryapi.exception.BusinessException;
import com.cursotdd.libraryapi.model.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class BookServiceTest {

    BookService service;
    @MockBean
    private BookRepository repository;

    @BeforeEach
    public void setUp () {
        this.service = new BookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve Salvar o livro.")
    void saveBookTest() {
        // cenário
        Book book = createValidBook();
        Mockito.when(repository.save(book)).thenReturn(
                Book.builder().id(11)
                        .isbn("123")
                        .title("As aventuras")
                        .author("Fulano")
                        .build());

        // execução
        Book savedBook = service.save(book);

        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getIsbn()).isEqualTo("123");
        assertThat(savedBook.getTitle()).isEqualTo("As aventuras");
        assertThat(savedBook.getAuthor()).isEqualTo("Fulano");
    }

    private Book createValidBook() {
        return Book.builder().isbn("123").author("Fulano").title("As aventuras").build();
    }

    @Test
    @DisplayName("Deve lançar erro de negócio ao tentar salvar um livro com isbn duplicado")
    public void shouldNotSaveABookDuplicateISBN () {
        // cenario
        Book book = createValidBook();
        Mockito.when(repository.existsByIsbn(Mockito.anyString())).thenReturn(true);
        // execucao
        Throwable exception = Assertions.catchThrowable(() -> service.save(book));
        // verificacoes
        assertThat(exception).isInstanceOf(BusinessException.class)
                .hasMessage("Isbn já cadastrado.");
        Mockito.verify(repository, Mockito.never()).save(book);
    }
}