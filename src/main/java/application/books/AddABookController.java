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

public class AddABookController implements Initializable {

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
    private ComboBox<String> languageType;

    @FXML
    private Button button_addBook;

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
    private Label titleError;

    @FXML
    private Label authorError;

    @FXML
    private Label genreError;

    @FXML
    private Label pagesError;

    @FXML
    private Label ratingError;

    @FXML
    private Label languageError;

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

        button_addBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String title = titleInput.getText();
                String author = authorInput.getText();
                String genre = genreInput.getText();
                Integer pages = 0;
                Float rating = 0.00f;
                String language = languageType.getSelectionModel().getSelectedItem();

                // Check if title is empty
                if (title.trim().isEmpty()) {
                    // Display error message
                    titleError.setText("Error: Title cannot be empty");
                    return;
                } else {
                    // Clear error message
                    titleError.setText("");
                }

                // Check if title already exists in the database
                String titleQuery = "SELECT title FROM books WHERE title='" + title + "'";
                try (Connection connection = new DatabaseConnection().getDBConnection();
                     Statement statement = connection.createStatement())
                {
                    ResultSet resultSet = statement.executeQuery(titleQuery);
                    if (resultSet.next()) {
                        // Display error message
                        titleError.setText("Error: Title already exists");
                        return;
                    } else {
                        // Clear error message
                        titleError.setText("");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Check if author is empty
                if (author.trim().isEmpty()) {
                    // Display error message
                    authorError.setText("Error: Author cannot be empty");
                    return;
                } else {
                    // Clear error message
                    authorError.setText("");
                }

                // Check if genre is empty
                if (genre.trim().isEmpty()) {
                    // Display error message
                    genreError.setText("Error: Genre cannot be empty");
                    return;
                } else {
                    // Clear error message
                    genreError.setText("");
                }

                // Check if pages is empty or not a number
                try {
                    pages = Integer.parseInt(pagesInput.getText());
                    pagesError.setText("");
                } catch (NumberFormatException e) {
                    // Display error message
                    pagesError.setText("Error: Pages must be a number");
                    return;
                }

                // Check if rating is a number with 2 decimals and within the range of 0 to 5
                try {
                    rating = Float.parseFloat(ratingInput.getText());
                    if (rating < 1 || rating > 5) {
                        // Display error message
                        ratingError.setText("Error: Rating must be between 1 and 5");
                        return;
                    }
                    // Convert int rating to float with 2 decimals
                    rating = (float) Math.round(rating * 100) / 100;
                    ratingError.setText("");
                } catch (NumberFormatException e) {
                    // Display error message
                    ratingError.setText("Error: Rating must be a number");
                    return;
                }

                // Check if language is selected
                if (language == null) {
                    // Display error message
                    languageError.setText("Error: Language must be selected");
                    return;
                } else {
                    // Clear error message
                    languageError.setText("");
                }

                String query = "INSERT INTO books(title, author, genre, pages, Goodreads_rating, language) " +
                        "VALUES('" + title + "', '" + author + "', '" + genre + "', " + pages + ", " + rating + ", '" + language + "')";

                try (Connection connection = new DatabaseConnection().getDBConnection();
                     Statement statement = connection.createStatement()) {
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    root = FXMLLoader.load(getClass().getResource("addABook.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });


        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getDBConnection();

        String query = "SELECT * FROM languages";
        try (Statement statement = connectDB.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ObservableList<String> languages = FXCollections.observableArrayList();
            while (resultSet.next()) {
                String language = resultSet.getString("language");
                languages.add(language);
            }
            languageType.setItems(languages);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadTable();
    }

    public void loadTable() {

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

            SortedList<Book> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(availableBooksTableView.comparatorProperty());

            availableBooksTableView.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(AddABookController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void setUserInformation(String user){
        User.username = user;
        label_user.setText("Hello, " + user);
    }

    public void clearBorrowedBooks() {
        for (Book book : bookObservableList) {
            book.borrowedBooksMap.clear();
        }
    }
}
