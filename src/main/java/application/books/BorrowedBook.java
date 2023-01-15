package application.books;

public class BorrowedBook {
    Integer user_id;
    Integer book_id;

    public BorrowedBook(Integer user_id, Integer book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
