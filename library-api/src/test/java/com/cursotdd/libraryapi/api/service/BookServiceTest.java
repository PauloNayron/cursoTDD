package com.cursotdd.libraryapi.api.service;

import com.cursotdd.libraryapi.api.model.entity.Book;
import com.cursotdd.libraryapi.api.service.impl.BookServiceImpl;
import com.cursotdd.libraryapi.model.repository.BookRepository;
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
        Book book = Book.builder().isbn("123").author("Fulano").title("As aventuras").build();
        Mockito.when(repository.save(book)).thenReturn(
                Book.builder().id(11)
                        .isbn("123")
                        .title("As aventuras")
                        .build());

        // execução
        Book savedBook = service.save(book);

        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getIsbn()).isEqualTo("123");
        assertThat(savedBook.getTitle()).isEqualTo("As aventuras");
        assertThat(savedBook.getAuthor()).isEqualTo("Fulano");
    }
}