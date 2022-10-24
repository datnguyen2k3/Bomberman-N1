package uet.oop.bomberman.entities.Score;

import uet.oop.bomberman.entities.Character.Enemy.BlueEnemy.Dora;
import uet.oop.bomberman.entities.Character.Enemy.BlueEnemy.Oneal;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.entities.Character.Enemy.RedEnemy.Doll;
import uet.oop.bomberman.entities.Character.Enemy.RedEnemy.Ghost;
import uet.oop.bomberman.entities.Character.Enemy.RedEnemy.RedCoin;
import uet.oop.bomberman.entities.Character.Enemy.YellowEnemy.Balloom;
import uet.oop.bomberman.entities.Character.Enemy.YellowEnemy.Cat;
import uet.oop.bomberman.entities.Character.Enemy.YellowEnemy.Coin;
import uet.oop.bomberman.entities.Character.Enemy.YellowEnemy.Minvo;

public class Score {
    public static int BALLOOM_POINT = 50;
    public static int DORA_POINT = 90;
    public static int ONEAL_POINT = 70;
    public static int DOLL_POINT = 130;
    public static int GHOST_POINT = 110;
    public static int RED_COIN_POINT = 220;
    public static int CAT_POINT = 80;
    public static int COIN_POINT = 160;
    public static int MINVO_POINT = 100;

    protected int currentScore = 0;

    public int getCurrentScore() {
        return currentScore;
    }

    public static int getScore(Enemy enemy) {
        if (enemy instanceof Balloom) {
            return BALLOOM_POINT;
        }
        if (enemy instanceof Dora) {
            return DORA_POINT;
        }
        if (enemy instanceof Oneal) {
            return ONEAL_POINT;
        }
        if (enemy instanceof Doll) {
            return DOLL_POINT;
        }
        if (enemy instanceof Ghost) {
            return GHOST_POINT;
        }
        if (enemy instanceof RedCoin) {
            return RED_COIN_POINT;
        }
        if (enemy instanceof Cat) {
            return CAT_POINT;
        }
        if (enemy instanceof Coin) {
            return COIN_POINT;
        }
        if (enemy instanceof Minvo) {
            return MINVO_POINT;
        }
        return 0;

    }

    public void addScore(Enemy enemy) {
        currentScore += getScore(enemy);
    }



}
