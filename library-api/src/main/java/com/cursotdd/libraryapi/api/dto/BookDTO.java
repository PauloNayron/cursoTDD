package com.cursotdd.libraryapi.api.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;
    @NotEmpty(message = "title nao pode ser nulo.")
    private String title;
    @NotEmpty(message = "author nao pode ser nulo.")
    private String author;
    @NotEmpty(message = "isbn nao pode ser nulo.")
    private String isbn;
}
