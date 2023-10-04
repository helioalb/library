package me.helioalbano.library.entrypoint.rest;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import me.helioalbano.library.domain.author.Author;
import me.helioalbano.library.entrypoint.rest.dto.AuthorResponse;
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

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> show(@PathVariable("authorId") Long authorId) {
        var author = authorService.findAuthorById(authorId);
        if (author.isPresent())
            return ResponseEntity.ok(buildAuthorResponse(author.get()));
        return ResponseEntity.notFound().build();
    }

    private AuthorResponse buildAuthorResponse(Author author) {
        return new AuthorResponse(author.fullName());
    }
}
