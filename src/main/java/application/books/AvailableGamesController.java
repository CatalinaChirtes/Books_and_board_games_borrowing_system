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

public class AvailableGamesController implements Initializable {

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
    private TableView<Game> availableGamesTableView;

    @FXML
    private TableColumn<Book, Integer> gameIDTableColumn;

    @FXML
    private TableColumn<Book, String> gameTableColumn;

    @FXML
    private TableColumn<Book, String> playersTableColumn;

    @FXML
    private TableColumn<Book, String> statusTableColumn;

    @FXML
    private TextField keywordTextField;

    ObservableList<Game> gameObservableList = FXCollections.observableArrayList();
    ObservableList<Game> availableGames = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        label_user.setText("Hello, " + User.username);

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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


        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getDBConnection();

        String gameViewQuery = "SELECT game_id, game, players, status FROM games";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(gameViewQuery);

            while(queryOutput.next()) {
                Integer queryGameID = queryOutput.getInt("game_id");
                String queryGame = queryOutput.getString("game");
                String queryPlayers = queryOutput.getString("players");
                String queryStatus = queryOutput.getString("status");

                gameObservableList.add(new Game(queryGameID, queryGame, queryPlayers, queryStatus));
            }

            gameIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("game_id"));
            gameTableColumn.setCellValueFactory(new PropertyValueFactory<>("game"));
            playersTableColumn.setCellValueFactory(new PropertyValueFactory<>("players"));

            // available books

            for (Game game : gameObservableList) {
                if (game.getStatus().equalsIgnoreCase("available")) {
                    availableGames.add(game);
                }
            }
            availableGamesTableView.setItems(availableGames);


            FilteredList<Game> filteredData = new FilteredList<>(availableGames, b -> true);

            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Game -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (Game.getGame().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (Game.getPlayers().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }   else {
                        return false;
                    }

                });
            });

            SortedList<Game> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(availableGamesTableView.comparatorProperty());

            availableGamesTableView.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(AvailableGamesController.class.getName()).log(Level.SEVERE, null, e);
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
}