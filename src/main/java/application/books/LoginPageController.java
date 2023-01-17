package application.books;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static application.books.Configurations.*;

public class LoginPageController implements Initializable {

    @FXML
    private Button button_login;

    @FXML
    private Button button_cancel;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private Label loginLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginPane.setBackground(new Background(new BackgroundFill(BACKGROUNDCOLOR, null, null)));
        loginLabel.setBackground(new Background(new BackgroundFill(LOGINCOLOR, null, null)));

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBConnection.logInUser(event, tf_username.getText(), tf_password.getText(), null);
            }
        });

        button_cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) button_cancel.getScene().getWindow();
                stage.close();
            }
        });
    }
}
