package com.cursotdd.libraryapi.api.service;

import com.cursotdd.libraryapi.api.model.entity.Book;
import org.springframework.stereotype.Service;


public interface BookService {
    Book save(Book book);
}
