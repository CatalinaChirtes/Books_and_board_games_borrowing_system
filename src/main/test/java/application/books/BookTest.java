package application.books;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testBorrowBook() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        book.borrowBook(1);
        assertEquals("borrowed", book.getStatus());
        assertTrue(book.borrowedBooksMap.containsKey(1));
    }

    @Test
    public void testReturnBook() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "borrowed");
        book.borrowedBooksMap.put(1, 1);
        book.returnBook(1);
        assertEquals("available", book.getStatus());
        assertFalse(book.borrowedBooksMap.containsKey(1));
    }

    @Test
    public void testIsBorrowable() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        assertTrue(book.isBorrowable(book));
    }

    @Test
    public void testIsReturnable() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "borrowed");
        assertTrue(book.isReturnable(book));
    }

    @Test
    public void testIsRemovable() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        assertTrue(book.isRemovable(book));
    }

    @Test
    public void testSetName() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        book.setName("Moby-Dick");
        assertEquals("Moby-Dick", book.getName());
    }

    @Test
    public void testGetBookId() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        assertEquals(1, book.getBook_id().intValue());
    }

    @Test
    public void testSetBookId() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        book.setBook_id(2);
        assertEquals(2, book.getBook_id().intValue());
    }

    @Test
    public void testGetTitle() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        assertEquals("The Great Gatsby", book.getTitle());
    }

    @Test
    public void testSetTitle() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        book.setTitle("The Catcher in the Rye");
        assertEquals("The Catcher in the Rye", book.getTitle());
    }

    @Test
    public void testGetAuthor() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        assertEquals("F. Scott Fitzgerald", book.getAuthor());
    }

    @Test
    public void testSetAuthor() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        book.setAuthor("J.D. Salinger");
        assertEquals("J.D. Salinger", book.getAuthor());
    }

    @Test
    public void testGetGenre() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        assertEquals("Novel", book.getGenre());
    }

    @Test
    public void testSetGenre() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        book.setGenre("Drama");
        assertEquals("Drama", book.getGenre());
    }

    @Test
    public void testGetPages() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        assertEquals(180, book.getPages().intValue());
    }

    @Test
    public void testSetPages() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        book.setPages(200);
        assertEquals(200, book.getPages().intValue());
    }

    @Test
    public void testGetGoodreadsRating() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        assertEquals(4.2f, book.getGoodreads_rating(), 0.01);
    }

    @Test
    public void testSetGoodreadsRating() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        book.setGoodreads_rating(4.5f);
        assertEquals(4.5f, book.getGoodreads_rating(), 0.01);
    }

    @Test
    public void testGetLanguage() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        assertEquals("English", book.getLanguage());
    }

    @Test
    public void testSetLanguage() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        book.setLanguage("German");
        assertEquals("German", book.getLanguage());
    }

    @Test
    public void testGetStatus() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        assertEquals("available", book.getStatus());
    }

    @Test
    public void testSetStatus() {
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 180, 4.2f, "English", "available");
        book.setStatus("borrowed");
        assertEquals("borrowed", book.getStatus());
    }
}