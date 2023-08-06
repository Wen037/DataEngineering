
package com.bookstoregroup.bookstoreartifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping
	public void addBook(@RequestBody Book book) {
		bookService.addBook(book);
	}

	@DeleteMapping("/{id}")
	public void removeBook(@PathVariable String id) {
		bookService.removeBook(id);
	}

	@PutMapping("/{id}")
	public void updateBookQuantity(@PathVariable String id, @RequestParam int quantity) {
		bookService.updateBookQuantity(id, quantity);
	}

    @GetMapping("/{id}")
    public ResponseEntity<Integer> getBookQuantity(@PathVariable String id) {
        try {
            int quantity = bookService.getBookQuantity(id);
            return new ResponseEntity<>(quantity, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

    @GetMapping("/search")
    public List<Book> searchBooks(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String isbn,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice,
        @RequestParam(required = false) Boolean available
    ) {
        return bookService.searchBooks(title, author, isbn, minPrice, maxPrice, available);
    }
}
