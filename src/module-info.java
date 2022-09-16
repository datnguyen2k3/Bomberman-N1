module Test {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens uet.oop.bomberman.entities;
    opens uet.oop.bomberman.graphics;
    opens uet.oop.bomberman;
}