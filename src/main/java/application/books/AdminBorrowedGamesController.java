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

public class AdminBorrowedGamesController implements Initializable {

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
    private Button switchToAvailableGames;

    @FXML
    private Button switchToBorrowedGames;

    @FXML
    private Button switchToAddAGame;

    @FXML
    private Button switchToRemoveAGame;

    @FXML
    private Button button_logout;

    @FXML
    private Label label_user;

    @FXML
    private TableView<Game> borrowedGamesTableView;

    @FXML
    private TableColumn<Game, Integer> gameIDTableColumn;

    @FXML
    private TableColumn<Game, String> gameTableColumn;

    @FXML
    private TableColumn<Game, String> playersTableColumn;

    @FXML
    private TableColumn<Game, String> userTableColumn;

    @FXML
    private TableColumn<Game, String> statusTableColumn;

    @FXML
    private TextField keywordTextField;

    ObservableList<Game> gameObservableList = FXCollections.observableArrayList();
    ObservableList<Game> borrowedGames = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        label_user.setText("Hello, " + User.username);

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearBorrowedGames();
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

        switchToAvailableGames.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("adminAvailableGames.fxml"));
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
                    root = FXMLLoader.load(getClass().getResource("adminBorrowedGames.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToAddAGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("addAGame.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        switchToRemoveAGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("removeAGame.fxml"));
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

        String gameViewQuery = "SELECT games.game_id, games.game, games.players, games.status, users.user FROM games JOIN borrowedgames ON games.game_id = borrowedgames.game_id JOIN users ON borrowedgames.user_id = users.user_id";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(gameViewQuery);

            while(queryOutput.next()) {
                Integer queryGameID = queryOutput.getInt("game_id");
                String queryGame = queryOutput.getString("game");
                String queryPlayers = queryOutput.getString("players");
                String queryStatus = queryOutput.getString("status");
                String queryUser = queryOutput.getString("user");

                gameObservableList.add(new Game(queryGameID, queryGame, queryPlayers, queryStatus));
            }

            gameIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("game_id"));
            gameTableColumn.setCellValueFactory(new PropertyValueFactory<>("game"));
            playersTableColumn.setCellValueFactory(new PropertyValueFactory<>("players"));
            userTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Game, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Game, String> game) {
                    try {
                        // Get the user_id of the user that borrowed the game from the borrowed games table
                        Statement statement1 = connectDB.createStatement();
                        ResultSet resultSet = statement1.executeQuery("SELECT user_id FROM borrowedgames WHERE game_id = " + game.getValue().getGame_id());
                        int userID = 0;
                        while (resultSet.next()) {
                            userID = resultSet.getInt("user_id");
                        }

                        // Get the username of the user from the user table using the user_id
                        Statement statement2 = connectDB.createStatement();
                        resultSet = statement2.executeQuery("SELECT user FROM users WHERE user_id = " + userID);
                        String username = "";
                        while (resultSet.next()) {
                            username = resultSet.getString("user");
                        }

                        return new SimpleStringProperty(username);
                    } catch (SQLException e) {
                        Logger.getLogger(AdminBorrowedGamesController.class.getName()).log(Level.SEVERE, null, e);
                    }
                    return new SimpleStringProperty("");
                }
            });

            // borrowed games

            for (Game game : gameObservableList) {
                if (game.getStatus().equalsIgnoreCase("borrowed")) {
                    borrowedGames.add(game);
                }
            }
            borrowedGamesTableView.setItems(borrowedGames);


            FilteredList<Game> filteredData = new FilteredList<>(borrowedGames, b -> true);

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

            sortedData.comparatorProperty().bind(borrowedGamesTableView.comparatorProperty());

            borrowedGamesTableView.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(AdminBorrowedBooksController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void setUserInformation(String user){
        User.username = user;
        label_user.setText("Hello, " + user);
    }

    public void clearBorrowedGames() {
        for (Game game : gameObservableList) {
            game.borrowedGamesMap.clear();
        }
    }
}