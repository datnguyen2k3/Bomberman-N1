package uet.oop.bomberman.utils;

import java.util.Random;

public enum State implements IState{
    GO_NORTH,
    GO_SOUTH,
    GO_WEST,
    GO_EAST,
    IDLE,
    DEAD;

    private static final Random PRNG = new Random();
    public static State getRandomState()  {
        State[] states = values();
        return states[PRNG.nextInt(states.length)];
    }
    private final int val = 1;
    @Override
    public int getVal() {
        return val;

    }
}
