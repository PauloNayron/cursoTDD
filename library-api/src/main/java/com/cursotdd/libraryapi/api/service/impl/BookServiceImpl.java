package com.cursotdd.libraryapi.api.service.impl;

import com.cursotdd.libraryapi.api.model.entity.Book;
import com.cursotdd.libraryapi.model.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements com.cursotdd.libraryapi.api.service.BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
