module application.books {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens application.books to javafx.fxml;
    exports application.books;
}