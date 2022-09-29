module Test {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens uet.oop.bomberman.entities;
    opens uet.oop.bomberman.graphics;
    opens uet.oop.bomberman;
    opens uet.oop.bomberman.entities.Item;
    opens uet.oop.bomberman.entities.Bomb;
    opens uet.oop.bomberman.entities.StillObject;
    opens uet.oop.bomberman.entities.Character;
}