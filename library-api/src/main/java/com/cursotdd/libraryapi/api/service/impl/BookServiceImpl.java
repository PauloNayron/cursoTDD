package com.cursotdd.libraryapi.api.service.impl;

import com.cursotdd.libraryapi.api.model.entity.Book;
import com.cursotdd.libraryapi.exception.BusinessException;
import com.cursotdd.libraryapi.model.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements com.cursotdd.libraryapi.api.service.BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        if (repository.existsByIsbn(book.getIsbn())) {
            throw new BusinessException("Isbn já cadastrado.");
        }
        return repository.save(book);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(Book book) {
        
    }

    @Override
    public Book update(Book book) {
        return null;
    }
}
