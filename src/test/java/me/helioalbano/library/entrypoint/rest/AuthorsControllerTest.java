package me.helioalbano.library.entrypoint.rest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import me.helioalbano.library.domain.author.Author;
import me.helioalbano.library.domain.author.Name;
import me.helioalbano.library.entrypoint.rest.dto.CreateAuthorRequest;
import me.helioalbano.library.service.AuthorService;

@WebMvcTest(AuthorsController.class)
public class AuthorsControllerTest {
    private ObjectMapper mapper = new ObjectMapper()
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    void givenAnAuthorWithNullNameWhenCreatingItThenReturnsAnError() throws Exception {
        var request = new CreateAuthorRequest(null);
        var mockRequest = MockMvcRequestBuilders.post("/authors/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.full_name", Is.is("cannot_be_blank")));
    }

    @Test
    void givenAnAuthorWithEmptyNameWhenCreatingItThenReturnsAnError() throws Exception {
        var request = new CreateAuthorRequest("");
        var mockRequest = MockMvcRequestBuilders.post("/authors/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.full_name", Is.is("cannot_be_blank")));
    }

    @Test
    void givenAnAuthorWithBlankNameWhenCreatingItThenReturnsAnError() throws Exception {
        var request = new CreateAuthorRequest("              ");
        var mockRequest = MockMvcRequestBuilders.post("/authors/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.full_name", Is.is("cannot_be_blank")));
    }

    @Test
    void givenAnAuthorWithNameGreaterThan100WhenCreatingItThenReturnsAnError() throws Exception {
        var request = new CreateAuthorRequest("a".repeat(101));
        var mockRequest = MockMvcRequestBuilders.post("/authors/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.full_name", Is.is("must_be_less_than_100")));
    }

    @Test
    void givenAValidAuthorGreaterThan100WhenCreatingItThenReturnsAnError() throws Exception {
        var authorFullName = "Machado de Assis";
        var request = new CreateAuthorRequest(authorFullName);
        var mockRequest = MockMvcRequestBuilders.post("/authors/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));
        when(authorService.createAuthor(anyString())).thenReturn(1L);

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/authors/1"));
    }

    @Test
    void givenAnInvalidAuthorIdWhenShowingItThenReturnStatusNotFound() throws Exception {
        var mockRequest = MockMvcRequestBuilders.get("/authors/999")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);
        when(authorService.findAuthorById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(mockRequest)
            .andExpect(status().isNotFound());
    }

    @Test
    void givenAValidAuthorIdWhenShowingItThenReturnAnAuthor() throws Exception {
        var mockRequest = MockMvcRequestBuilders.get("/authors/1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);
        var author = new Author(1L, new Name("David Thomas"));
        when(authorService.findAuthorById(1L)).thenReturn(Optional.of(author));

        mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.full_name", Is.is("David Thomas")));
    }
}
