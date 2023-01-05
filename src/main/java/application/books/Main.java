package application.books;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Image icon = new Image("C:\\Java\\Books\\src\\book.png");
        stage.getIcons().add(icon);
        stage.setTitle("Login");
        stage.setScene((new Scene(root)));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}