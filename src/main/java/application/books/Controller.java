package application.books;

import connection.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    public TextField textField;
    public Label textLabel;

    public void button(ActionEvent actionEvent) throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql = "INSERT INTO BOOKS(TITLE) VALUES('"+textField.getText()+"')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

        sql = "SELECT TITLE FROM BOOKS;";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            textLabel.setText(resultSet.getString(1));
        }
        //textLabel.setText(textField.getText());
    }
}

//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }