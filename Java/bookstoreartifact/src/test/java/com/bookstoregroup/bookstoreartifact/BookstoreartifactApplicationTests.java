package com.bookstoregroup.bookstoreartifact;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookstoreartifactApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Test
	void contextLoads() {
	}

	@Test
	void getAllBooksTest() throws Exception {
		Book book = new Book("1", "Test Book", "Test Author", "1234567890", 100, 19.99, true);
		given(bookService.getAllBooks()).willReturn(Arrays.asList(book));
	
		mockMvc.perform(get("/books")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getBookQuantityTest() throws Exception {
		String bookId = "1";
		int quantity = 100;
		given(bookService.getBookQuantity(bookId)).willReturn(quantity);

		mockMvc.perform(get("/books/" + bookId)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getBookQuantityNotFoundTest() throws Exception {
		String bookId = "2";
		given(bookService.getBookQuantity(bookId)).willThrow(new SQLException("Book not found"));

		mockMvc.perform(get("/books/" + bookId)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void addBookTest() throws Exception {
		Book book = new Book("1", "Test Book", "Test Author", "1234567890", 100, 19.99, true);
		doAnswer(invocation -> {
			Object arg0 = invocation.getArgument(0);
			assertEquals(book, arg0);
			return null;
		}).when(bookService).addBook(any(Book.class));
	
		mockMvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\": \"1\", \"title\": \"Test Book\", \"author\": \"Test Author\", \"isbn\": \"1234567890\", \"quantity\": 100, \"price\": 19.99, \"availability\": true}"))
				.andExpect(status().isCreated());  // Change this line
	}
	


	@Test
	void removeBookTest() throws Exception {
		String bookId = "1";
		doNothing().when(bookService).removeBook(bookId);

		mockMvc.perform(delete("/books/" + bookId)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void updateBookQuantityTest() throws Exception {
		String bookId = "1";
		int quantity = 200;
	
		// Given the book exists
		doNothing().when(bookService).updateBookQuantity(bookId, quantity);
	
		// When we make a PUT request to update the book's quantity
		mockMvc.perform(put("/books/" + bookId)
				.param("quantity", String.valueOf(quantity)))
				// Then the status should be OK
				.andExpect(status().isOk());
	
		// Verify that the bookService's updateBookQuantity method was called
		verify(bookService, times(1)).updateBookQuantity(bookId, quantity);
	}

	@Test
	void searchBooksTest() throws Exception {
		Book book = new Book("1", "Test Book", "Test Author", "1234567890", 100, 19.99, true);
		given(bookService.searchBooks(any(), any(), any(), any(), any(), any())).willReturn(Arrays.asList(book));
	
		mockMvc.perform(get("/books/search")
				.param("title", "Test Book")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getAllBooksExceptionTest() throws Exception {
		// Given that calling getAllBooks on the bookService throws a RuntimeException
		given(bookService.getAllBooks()).willThrow(new RuntimeException("Database error"));
	
		// When we make a GET request to the "/books" endpoint
		mockMvc.perform(get("/books")
				.contentType(MediaType.APPLICATION_JSON))
				// Then the response should have a 500 status code (Internal Server Error)
				.andExpect(status().isInternalServerError());
	}

	@Test
	void exceptionHandlerTest() throws Exception {
		// Given that calling getAllBooks on the bookService throws a RuntimeException
		given(bookService.getAllBooks()).willThrow(new RuntimeException("Database error"));
	
		// When we make a GET request to the "/books" endpoint
		mockMvc.perform(get("/books")
				.contentType(MediaType.APPLICATION_JSON))
				// Then the response should have a 500 status code (Internal Server Error)
				.andExpect(status().isInternalServerError());
	}
}
