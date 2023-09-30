package me.helioalbano.library.entrypoint.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.helioalbano.library.entrypoint.rest.dto.CreateBookRequest;
import me.helioalbano.library.service.BookService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.core.Is;

@WebMvcTest(BooksController.class)
public class BooksControllerTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void givenABookWithNullTitleWhenCreatingThenReturnAnError() throws Exception {
        var request = new CreateBookRequest(null);
        var mockRequest = MockMvcRequestBuilders.post("/books/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request));

        mockMvc.perform(mockRequest)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.title", Is.is("cannot_be_blank")));
    }

    @Test
    void givenABookWithEmptyTitleWhenCreatingThenReturnAnError() throws Exception {
        var request = new CreateBookRequest("");
        var mockRequest = MockMvcRequestBuilders.post("/books/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request));

        mockMvc.perform(mockRequest)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.title", Is.is("cannot_be_blank")));
    }

    @Test
    void givenABookWithBlankTitleWhenCreatingThenReturnAnError() throws Exception {
        var request = new CreateBookRequest("                      ");
        var mockRequest = MockMvcRequestBuilders.post("/books/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request));

        mockMvc.perform(mockRequest)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.title", Is.is("cannot_be_blank")));
    }

    @Test
    void givenABookWithTitleGreaterThan100WhenCreatingThenReturnAnError() throws Exception {
        var request = new CreateBookRequest("a".repeat(101));
        var mockRequest = MockMvcRequestBuilders.post("/books/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request));

        mockMvc.perform(mockRequest)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.title", Is.is("must_be_less_than_100")));
    }

    @Test
    void givenAValideBookWhenCreatingThenReturnAnError() throws Exception {
        var request = new CreateBookRequest("Código limpo");
        var mockRequest = MockMvcRequestBuilders.post("/books/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request));
        when(bookService.createBook("Código limpo")).thenReturn(1L);

        mockMvc.perform(mockRequest)
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/books/1"));
        }
}
