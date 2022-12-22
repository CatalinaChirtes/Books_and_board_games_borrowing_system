package application.books;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Image icon = new Image("C:\\Java\\Books\\src\\book.png");
        stage.getIcons().add(icon);
        stage.setTitle("Books in my library");
        stage.setScene((new Scene(root,600,575)));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setScene(scene);