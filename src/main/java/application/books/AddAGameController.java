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

public class AddAGameController implements Initializable {

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
    private TextField gameInput;

    @FXML
    private TextField playersInput;

    @FXML
    private Button button_addGame;

    @FXML
    private TableView<Game> availableGamesTableView;

    @FXML
    private TableColumn<Game, Integer> gameIDTableColumn;

    @FXML
    private TableColumn<Game, String> gameTableColumn;

    @FXML
    private TableColumn<Game, String> playersTableColumn;

    @FXML
    private TableColumn<Game, String> statusTableColumn;

    @FXML
    private Label gameError;

    @FXML
    private Label playersError;

    ObservableList<Game> gameObservableList = FXCollections.observableArrayList();
    ObservableList<Game> availableGames = FXCollections.observableArrayList();

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

        button_addGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String game = gameInput.getText();
                String players = playersInput.getText();

                // Check if game is empty
                if (game.trim().isEmpty()) {
                    // Display error message
                    gameError.setText("Error: Game cannot be empty");
                    return;
                } else {
                    // Clear error message
                    gameError.setText("");
                }

                // Check if game already exists in the database
                String gameQuery = "SELECT game FROM games WHERE game='" + game + "'";
                try (Connection connection = new DatabaseConnection().getDBConnection();
                     Statement statement = connection.createStatement())
                {
                    ResultSet resultSet = statement.executeQuery(gameQuery);
                    if (resultSet.next()) {
                        // Display error message
                        gameError.setText("Error: Game already exists");
                        return;
                    } else {
                        // Clear error message
                        gameError.setText("");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Check if players is empty
                if (players.trim().isEmpty()) {
                    // Display error message
                    playersError.setText("Error: Players number cannot be empty");
                    return;
                } else {
                    // Clear error message
                    playersError.setText("");
                }

                String query = "INSERT INTO games(game, players) " +
                        "VALUES('" + game + "', '" + players + "')";

                try (Connection connection = new DatabaseConnection().getDBConnection();
                     Statement statement = connection.createStatement()) {
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    root = FXMLLoader.load(getClass().getResource("addAGame.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        loadTable();
    }

    public void loadTable() {

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

            // available games

            for (Game game : gameObservableList) {
                if (game.getStatus().equalsIgnoreCase("available")) {
                    availableGames.add(game);
                }
            }
            availableGamesTableView.setItems(availableGames);


            FilteredList<Game> filteredData = new FilteredList<>(availableGames, b -> true);

            SortedList<Game> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(availableGamesTableView.comparatorProperty());

            availableGamesTableView.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(AddABookController.class.getName()).log(Level.SEVERE, null, e);
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