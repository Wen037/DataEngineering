package com.bookstoregroup.bookstoreartifact;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);


	private Connection connection;

	public BookService() {
		try {
			this.connection = DBConnection.getConnection();
		} catch (SQLException e) {
			log.error("Error with connection: ", e);
		}
	}

    public void addBook(Book book) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO books (id, title, author, isbn, quantity, price, availability) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            ps.setString(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getIsbn());
            ps.setInt(5, book.getQuantity());
            ps.setDouble(6, book.getPrice());
            ps.setBoolean(7, book.isAvailable());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error adding book: ", e);
            throw new RuntimeException("Database error", e);
        }
    }

	public void removeBook(String id) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM books WHERE id = ?");
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
            log.error("Error removing book: ", e);
            throw new RuntimeException("Database error", e);
		}
	}

    public void updateBookQuantity(String id, int quantity) {
        try {
            // Check if the book exists
            PreparedStatement psCheck = connection.prepareStatement("SELECT * FROM books WHERE id = ?");
            psCheck.setString(1, id);
            ResultSet rsCheck = psCheck.executeQuery();
            if (!rsCheck.next()) {
                throw new SQLException("Book not found");
            }
    
            // Update quantity
            PreparedStatement ps = connection.prepareStatement("UPDATE books SET quantity = ? WHERE id = ?");
            ps.setInt(1, quantity);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error updating book quantity: ", e);
            throw new RuntimeException("Database error", e);
        }
    }

    public int getBookQuantity(String id) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT quantity FROM books WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            } else {
                throw new SQLException("Book not found");
            }
        } catch (SQLException e) {
            log.error("Error getting book quantity: ", e);
            throw new RuntimeException("Database error", e);
        }
    }
    

public List<Book> getAllBooks() {
    List<Book> books = new ArrayList<>();
    try {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM books");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Book book = new Book(
                rs.getString("id"), 
                rs.getString("title"), 
                rs.getString("author"), 
                rs.getString("isbn"), 
                rs.getInt("quantity"), 
                rs.getDouble("price"), 
                rs.getBoolean("availability")
            );
            books.add(book);
        }
    } catch (SQLException e) {
        log.error("Error getting all books: ", e);
        throw new RuntimeException("Database error", e);
    }
    return books;
}



    public List<Book> searchBooks(String title, String author, String isbn, Double minPrice, Double maxPrice, Boolean available) {
        List<Book> books = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM books WHERE 1=1");
            if (title != null) {
                sql.append(" AND title LIKE ?");
            }
            if (author != null) {
                sql.append(" AND author LIKE ?");
            }
            if (isbn != null) {
                sql.append(" AND isbn LIKE ?");
            }
            if (minPrice != null) {
                sql.append(" AND price >= ?");
            }
            if (maxPrice != null) {
                sql.append(" AND price <= ?");
            }
            if (available != null) {
                sql.append(" AND available = ?");
            }
            PreparedStatement ps = connection.prepareStatement(sql.toString());
    
            int paramIndex = 1;
            if (title != null) {
                ps.setString(paramIndex++, "%" + title + "%");
            }
            if (author != null) {
                ps.setString(paramIndex++, "%" + author + "%");
            }
            if (isbn != null) {
                ps.setString(paramIndex++, "%" + isbn + "%");
            }
            if (minPrice != null) {
                ps.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                ps.setDouble(paramIndex++, maxPrice);
            }
            if (available != null) {
                ps.setBoolean(paramIndex++, available);
            }
    
            ResultSet rs = ps.executeQuery();
    
            while (rs.next()) {
                Book book = new Book(
                    rs.getString("id"), 
                    rs.getString("title"), 
                    rs.getString("author"), 
                    rs.getString("isbn"), 
                    rs.getInt("quantity"), 
                    rs.getDouble("price"), 
                    rs.getBoolean("availability")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            log.error("Error searching books: ", e);
            throw new RuntimeException("Database error", e);
        }
        return books;
    }
}
