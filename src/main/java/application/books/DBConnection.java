package application.books;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBConnection {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if(username != null) {
            try {
                if(username.equals("admin")) {
                    FXMLLoader loader = new FXMLLoader(DBConnection.class.getResource(fxmlFile));
                    root = loader.load();
                    AdminController adminController = loader.getController();
                    adminController.setUserInformation(username);
                } else {
                    FXMLLoader loader = new FXMLLoader(DBConnection.class.getResource(fxmlFile));
                    root = loader.load();
                    ReaderController readerController = loader.getController();
                    readerController.setUserInformation(username);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBConnection.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void logInUser(ActionEvent event, String user, String password) {
        String dbName = "bookdatabase";
        String userName = "root";
        String passWord = "";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,passWord);
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE user = ?");
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                //System.out.println("User not found in the database.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The provided credentials are incorrect. User not found in the database.");
                alert.show();
            } else {
                while(resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        if(user.equals("admin")) {
                            changeScene(event, "admin.fxml", "Welcome to the library dear admin!", user);
                        } else {
                            changeScene(event, "reader.fxml", "Welcome to the library dear reader!", user);
                        }
                    } else {
                        //System.out.println("Passwords didn't match.");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect. Passwords didn't match.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
