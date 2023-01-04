package application.books;

public class Book {
    Integer book_id;
    String title;
    String author;
    String genre;
    Integer pages;
    Float Goodreads_rating;
    String language;
    String status;

    public Book(Integer book_id, String title, String author, String genre, Integer pages, Float goodreads_rating, String language, String status) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.Goodreads_rating = goodreads_rating;
        this.language = language;
        this.status = status;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Float getGoodreads_rating() {
        return Goodreads_rating;
    }

    public void setGoodreads_rating(Float goodreads_rating) {
        Goodreads_rating = goodreads_rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
