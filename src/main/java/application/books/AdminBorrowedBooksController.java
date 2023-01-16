package application.books;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminBorrowedBooksController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button switchToAvailableBooks;

    @FXML
    private Button switchToBorrowedBooks;

    @FXML
    private Button switchToAddABook;

    @FXML
    private Button switchToRemoveABook;

    @FXML
    private Button switchToAddUser;

    @FXML
    private Button button_logout;

    @FXML
    private Label label_user;

    @FXML
    private TableView<Book> borrowedBooksTableView;

    @FXML
    private TableColumn<Book, Integer> bookIDTableColumn;

    @FXML
    private TableColumn<Book, String> titleTableColumn;

    @FXML
    private TableColumn<Book, String> authorTableColumn;

    @FXML
    private TableColumn<Book, String> genreTableColumn;

    @FXML
    private TableColumn<Book, Integer> pagesTableColumn;

    @FXML
    private TableColumn<Book, Float> ratingTableColumn;

    @FXML
    private TableColumn<Book, String> languageTableColumn;

    @FXML
    private TableColumn<Book, String> userTableColumn;

    @FXML
    private TableColumn<Book, String> statusTableColumn;

    @FXML
    private TextField keywordTextField;

    ObservableList<Book> bookObservableList = FXCollections.observableArrayList();
    ObservableList<Book> borrowedBooks = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        label_user.setText("Hello, " + User.username);

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearBorrowedBooks();
                DBConnection.changeScene(event, "login.fxml", "Login", null, null);
            }
        });

        switchToAvailableBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToBorrowedBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("adminBorrowedBooks.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToAddABook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("addABook.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToRemoveABook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("removeABook.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToAddUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("addANewUser.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getDBConnection();

        //String bookViewQuery = "SELECT book_id, title, author, genre, pages, Goodreads_rating, language, status FROM books";
        String bookViewQuery = "SELECT books.book_id, books.title, books.author, books.genre, books.pages, books.Goodreads_rating, books.language, books.status, users.user FROM books JOIN borrowedbooks ON books.book_id = borrowedbooks.book_id JOIN users ON borrowedbooks.user_id = users.user_id";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(bookViewQuery);

            while(queryOutput.next()) {
                Integer queryBookID = queryOutput.getInt("book_id");
                String queryTitle = queryOutput.getString("title");
                String queryAuthor = queryOutput.getString("author");
                String queryGenre = queryOutput.getString("genre");
                Integer queryPages = queryOutput.getInt("pages");
                Float queryRating = queryOutput.getFloat("Goodreads_rating");
                String queryLanguage = queryOutput.getString("language");
                String queryStatus = queryOutput.getString("status");
                String queryUser = queryOutput.getString("user");
                bookObservableList.add(new Book(queryBookID, queryTitle, queryAuthor, queryGenre, queryPages, queryRating, queryLanguage, queryStatus));
            }

            bookIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("book_id"));
            titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorTableColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
            genreTableColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
            pagesTableColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
            ratingTableColumn.setCellValueFactory(new PropertyValueFactory<>("Goodreads_rating"));
            ratingTableColumn.setCellFactory(column -> {
                TableCell<Book, Float> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(Float item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            DecimalFormat df = new DecimalFormat("#.00");
                            setText(df.format(item));
                        }
                    }
                };
                return cell;
            });
            languageTableColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
            //userTableColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
            userTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Book, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> book) {
                    try {
                        // Get the user_id of the user that borrowed the book from the borrowed books table
                        int userID = getUserIDFromBorrowedBooksTable(book.getValue().getBook_id());
                        // Get the username of the user from the user table using the user_id
                        String username = getUsernameFromUserTable(userID);
                        return new SimpleStringProperty(username);
                    } catch (SQLException e) {
                        Logger.getLogger(AdminBorrowedBooksController.class.getName()).log(Level.SEVERE, null, e);
                    }
                    return new SimpleStringProperty("");
                }
            });

            // borrowed books

            for (Book book : bookObservableList) {
                if (book.getStatus().equalsIgnoreCase("borrowed")) {
                    borrowedBooks.add(book);
                }
            }
            borrowedBooksTableView.setItems(borrowedBooks);


            FilteredList<Book> filteredData = new FilteredList<>(borrowedBooks, b -> true);

            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Book -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (Book.getTitle().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (Book.getAuthor().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (Book.getGenre().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (Book.getLanguage().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else {
                        return false;
                    }

                });
            });

            SortedList<Book> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(borrowedBooksTableView.comparatorProperty());

            borrowedBooksTableView.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(AdminBorrowedBooksController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void setUserInformation(String user){
        User.username = user;
        label_user.setText("Hello, " + user);
    }

    private int getUserIDFromBorrowedBooksTable(int bookID) throws SQLException {
        // Create a connection to the database
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getDBConnection();
        Statement statement = connectDB.createStatement();
        // Execute a query to get the user_id from the borrowed books table
        ResultSet resultSet = statement.executeQuery("SELECT user_id FROM borrowedbooks WHERE book_id = " + bookID);
        int userID = 0;
        while (resultSet.next()) {
            userID = resultSet.getInt("user_id");
        }
        return userID;
    }

    private String getUsernameFromUserTable(int userID) throws SQLException {
        // Create a connection to the database
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getDBConnection();
        Statement statement = connectDB.createStatement();
        // Execute a query to get the username from the user table
        ResultSet resultSet = statement.executeQuery("SELECT user FROM users WHERE user_id = " + userID);
        String username = "";
        while (resultSet.next()) {
            username = resultSet.getString("user");
        }
        return username;
    }

    public void clearBorrowedBooks() {
        for (Book book : bookObservableList) {
            book.borrowedBooksMap.clear();
        }
    }
}