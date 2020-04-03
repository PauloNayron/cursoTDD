package com.cursotdd.libraryapi.api.service;

import com.cursotdd.libraryapi.api.model.entity.Book;

import java.util.Optional;

public interface BookService {
    Book save(Book book);

    Optional<Book> getById(Long id);
}
