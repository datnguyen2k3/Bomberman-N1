module Bomberman {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;

    opens uet.oop.bomberman.entities;
    opens uet.oop.bomberman.graphics;
    opens uet.oop.bomberman;
    opens uet.oop.bomberman.entities.Item;
    opens uet.oop.bomberman.entities.Bomb;
    opens uet.oop.bomberman.entities.StillObject;
    opens uet.oop.bomberman.entities.Character;
    opens uet.oop.bomberman.entities.Character.Enemy;
    opens uet.oop.bomberman.UI.Menu;
    opens uet.oop.bomberman.UI.GameUI;
    opens uet.oop.bomberman.entities.Character.Enemy.BlueEnemy;
    opens uet.oop.bomberman.entities.Character.Enemy.YellowEnemy;
    opens uet.oop.bomberman.entities.Character.Enemy.RedEnemy;

}