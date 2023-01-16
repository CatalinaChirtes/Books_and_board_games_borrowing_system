package application.books;

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

public class BorrowABookController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button switchToAvailableBooks;

    @FXML
    private Button switchToBorrowedBooks;

    @FXML
    private Button switchToBorrowABook;

    @FXML
    private Button switchToDonateABook;

    @FXML
    private Button switchToReturnABook;

    @FXML
    private Button switchToAvailableGames;

    @FXML
    private Button switchToBorrowedGames;

    @FXML
    private Button switchToBorrowAGame;

    @FXML
    private Button switchToReturnAGame;

    @FXML
    private Button button_logout;

    @FXML
    private Label label_user;

    @FXML
    private TableView<Book> availableBooksTableView;

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
    private TableColumn<Book, String> statusTableColumn;

    @FXML
    private TextField keywordTextField;

    @FXML
    private TextField titleInput;

    @FXML
    private TextField authorInput;

    @FXML
    private TextField genreInput;

    @FXML
    private TextField pagesInput;

    @FXML
    private TextField ratingInput;

    @FXML
    private TextField languageInput;

    @FXML
    private Button button_borrowBook;

    ObservableList<Book> bookObservableList = FXCollections.observableArrayList();
    ObservableList<Book> availableBooks = FXCollections.observableArrayList();


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
                    root = FXMLLoader.load(getClass().getResource("reader.fxml"));
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
                    root = FXMLLoader.load(getClass().getResource("borrowedBooks.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToBorrowABook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("borrowABook.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToDonateABook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("donateABook.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToReturnABook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("returnABook.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToAvailableGames.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("availableGames.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToBorrowedGames.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("borrowedGames.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToBorrowAGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("borrowAGame.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToReturnAGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("returnAGame.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        button_borrowBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Book selectedBook = availableBooksTableView.getSelectionModel().getSelectedItem();

                if (selectedBook != null && selectedBook.isBorrowable(selectedBook)) {
                    selectedBook.borrowBook(User.userID);

                    int user_id = selectedBook.borrowedBooksMap.keySet().iterator().next();
                    int book_id = selectedBook.borrowedBooksMap.values().iterator().next();

                    // update status in database
                    DatabaseConnection connection = new DatabaseConnection();
                    Connection conn = connection.getDBConnection();

                    String updateSql = "UPDATE books SET status='borrowed' WHERE title='" + selectedBook.getTitle() + "' AND author='" + selectedBook.getAuthor() + "'";
                    String insertSql = "INSERT INTO borrowedbooks (user_id, book_id) VALUES ('" + user_id + "', '" + book_id + "')";
                    try {
                        Statement statement = conn.createStatement();
                        statement.executeUpdate(insertSql);
                        statement.executeUpdate(updateSql);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    try {
                        root = FXMLLoader.load(getClass().getResource("borrowABook.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        });
        loadTable();
    }

    public void loadTable(){
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getDBConnection();

        String bookViewQuery = "SELECT book_id, title, author, genre, pages, Goodreads_rating, language, status FROM books";

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

            // available books

            for (Book book : bookObservableList) {
                if (book.getStatus().equalsIgnoreCase("available")) {
                    availableBooks.add(book);
                }
            }
            availableBooksTableView.setItems(availableBooks);


            FilteredList<Book> filteredData = new FilteredList<>(availableBooks, b -> true);

            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Book -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (Book.getTitle().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else {
                        return false;
                    }

                });
            });

            SortedList<Book> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(availableBooksTableView.comparatorProperty());

            availableBooksTableView.setItems(sortedData);

            availableBooksTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldBook, newBook) -> {
                if (newBook != null) {
                    titleInput.setText(newBook.getTitle());
                    authorInput.setText(newBook.getAuthor());
                    genreInput.setText(newBook.getGenre());
                    pagesInput.setText(String.valueOf(newBook.getPages()));
                    DecimalFormat df = new DecimalFormat("#.00");
                    ratingInput.setText(df.format(newBook.getGoodreads_rating()));
                    languageInput.setText(newBook.getLanguage());
                }
            });

        } catch (SQLException e) {
            Logger.getLogger(BorrowABookController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void setUserInformation(String user){
        User.username = user;
        label_user.setText("Hello, " + user);
    }

    public void setUserID(Integer id) {
        User.userID = id;
    }

    public void clearBorrowedBooks() {
        for (Book book : bookObservableList) {
            book.borrowedBooksMap.clear();
        }
    }
}
