package me.helioalbano.library.entrypoint.rest;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import me.helioalbano.library.entrypoint.rest.dto.CreateAuthorRequest;
import me.helioalbano.library.service.AuthorService;

@Controller
@RequestMapping("authors")
public class AuthorsController {

    private AuthorService authorService;

    public AuthorsController(final AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(@Validated @RequestBody CreateAuthorRequest input) {
        var authorId = authorService.createAuthor(input.fullName());
        return ResponseEntity.created(buildResourcePath(authorId)).build();
    }

    private URI buildResourcePath(Long authorId) {
        return URI.create("/authors/" + authorId);
    }

}
