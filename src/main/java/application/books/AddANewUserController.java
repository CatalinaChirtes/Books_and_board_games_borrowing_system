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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddANewUserController implements Initializable {

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
    private TextField userInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button button_addUser;

    @FXML
    private TableView<User> UsersTableView;

    @FXML
    private TableColumn<User, Integer> userIDTableColumn;

    @FXML
    private TableColumn<User, String> userTableColumn;

    @FXML
    private Label usernameError;

    @FXML
    private Label passwordError;

    ObservableList<User> userObservableList = FXCollections.observableArrayList();
    ObservableList<User> userList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        label_user.setText("Hello, " + User.username);

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBConnection.changeScene(event, "login.fxml", "Login", null);
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

        button_addUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = userInput.getText();
                String password = passwordInput.getText();

                if (username.trim().isEmpty()) {
                    usernameError.setText("Error: Username cannot be empty");
                    return;
                } else {
                    usernameError.setText("");
                }

                String userQuery = "SELECT user FROM users WHERE user='" + username + "'";
                try (Connection connection = new DatabaseConnection().getDBConnection();
                     Statement statement = connection.createStatement())
                {
                    ResultSet resultSet = statement.executeQuery(userQuery);
                    if (resultSet.next()) {
                        usernameError.setText("Error: User already exists");
                        return;
                    } else {
                        usernameError.setText("");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if (password.trim().isEmpty()) {
                    passwordError.setText("Error: Password cannot be empty");
                    return;
                } else {
                    passwordError.setText("");
                }

                String query = "INSERT INTO users(user, password) " +
                        "VALUES('" + username + "', '" + password + "')";

                try (Connection connection = new DatabaseConnection().getDBConnection();
                     Statement statement = connection.createStatement()) {
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    root = FXMLLoader.load(getClass().getResource("addANewUser.fxml"));
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
        String userViewQuery = "SELECT user_id, user, password FROM users";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(userViewQuery);

            while(queryOutput.next()) {
                Integer queryUserID = queryOutput.getInt("user_id");
                String queryUser = queryOutput.getString("user");
                String queryPassword = queryOutput.getString("password");

                userObservableList.add(new User(queryUserID, queryUser, queryPassword));
            }

            userIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            userTableColumn.setCellValueFactory(new PropertyValueFactory<>("user"));

            for (User user : userObservableList) {
                if (!user.getUser().equalsIgnoreCase("admin")) {
                    userList.add(user);
                }
            }
            UsersTableView.setItems(userList);


            FilteredList<User> filteredData = new FilteredList<>(userList, b -> true);

            SortedList<User> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(UsersTableView.comparatorProperty());

            UsersTableView.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(AddANewUserController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void setUserInformation(String user){
        User.username = user;
        label_user.setText("Hello, " + user);
    }
}
