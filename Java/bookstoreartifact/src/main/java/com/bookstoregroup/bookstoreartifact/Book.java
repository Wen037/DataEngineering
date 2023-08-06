
package com.bookstoregroup.bookstoreartifact;

import java.util.Objects;

public class Book {
	private String id;
	private String title;
	private String author;
	private String isbn;
	private int quantity;
	private double price;
	private boolean availability;

	public Book(String id, String title, String author, String isbn, int quantity, double price, boolean availability) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.quantity = quantity;
		this.price = price;
		this.availability = availability;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return quantity == book.quantity &&
			Double.compare(book.price, price) == 0 &&
			availability == book.availability &&
			Objects.equals(id, book.id) &&
			Objects.equals(title, book.title) &&
			Objects.equals(author, book.author) &&
			Objects.equals(isbn, book.isbn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, author, isbn, quantity, price, availability);
	}
}