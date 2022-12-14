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
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage.setScene((new Scene(root)));

//        Group root = new Group();
//        Scene scene = new Scene(root, 600, 600, Color.CHOCOLATE);

        Image icon = new Image("C:\\Java\\Books\\src\\book.png");
        stage.getIcons().add(icon);
        stage.setTitle("Books in my library");
//        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}