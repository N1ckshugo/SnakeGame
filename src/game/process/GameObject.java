package game.process;

import static settings.SettingConstant.*;

public class GameObject {   //Базовый игровой объект

    private int x;
    private int y;

    protected GameObject() {
    }

    protected GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getGlobalX() {
        return gameToGlobalX(x);
    }

    public int getGlobalY() {
        return gameToGlobalY(y);
    }

    private int globalToGameCoordinate(int coordinate) {
        return (coordinate - INDENT) / CELL + 1;
    }

    private int gameToGlobalX(int coordinate) {
        return (coordinate - 1) * CELL;
    }

    private int gameToGlobalY(int coordinate) {
        return (coordinate - 1) * CELL + INDENT;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
