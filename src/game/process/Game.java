package game.process;

import settings.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static settings.SettingConstant.*;

public class Game {

    private final int record;
    private final Snake snake;
    private final GameObject eat;
    private boolean gameOver;
    private Direction dir;
    private Image headImg;
    private Image bodyImg;
    private Image eatImg;
    private Image gameOverImg;

    public Game() {
        try {
            this.headImg = ImageIO.read(new File("src/resource/head.png")).getScaledInstance(CELL, CELL, Image.SCALE_DEFAULT);
            this.bodyImg = ImageIO.read(new File("src/resource/body.png")).getScaledInstance(CELL, CELL, Image.SCALE_DEFAULT);
            this.eatImg = ImageIO.read(new File("src/resource/eat.png")).getScaledInstance(CELL, CELL, Image.SCALE_DEFAULT);
            this.gameOverImg = ImageIO.read(new File("src/resource/game_over.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        record = loadRecord();
        dir = START_DIRECTION;
        snake = new Snake(START_SNAKE_X, START_SNAKE_Y);
        eat = new GameObject();
        moveEat();
    }

    public void gameProgress() {
        snakeMove();
        isCollision();
        if (isGameOver()) {
            saveRecord();
        }
    }

    private void snakeMove() {
        if (checkEat()) {
            snake.addBodyPart(new GameObject(snake.getX(), snake.getY()));
            moveEat();
        }

        for (int i = snake.getBodyParts().size() - 1; i >= 0; i--) {
            if (i == 0) {
                snake.getBodyParts().get(0).setX(snake.getX());
                snake.getBodyParts().get(0).setY(snake.getY());
            } else {
                int nextX = snake.getBodyParts().get(i - 1).getX();
                int nextY = snake.getBodyParts().get(i - 1).getY();
                snake.getBodyParts().get(i).setX(nextX);
                snake.getBodyParts().get(i).setY(nextY);
            }
        }

        switch (dir) {
            case LEFT -> {
                if (snake.getX() <= 1) {
                    snake.setX(WIDTH_GAME + 1);
                }
                snake.setX(snake.getX() - 1);
            }
            case UP -> {
                if (snake.getY() <= 1) {
                    snake.setY(HEIGHT_GAME + 1);
                }
                snake.setY(snake.getY() - 1);
            }
            case RIGHT -> {
                if (snake.getX() >= WIDTH_GAME) {
                    snake.setX(0);
                }
                snake.setX(snake.getX() + 1);
            }
            case DOWN -> {
                if (snake.getY() >= HEIGHT_GAME) {
                    snake.setY(0);
                }
                snake.setY(snake.getY() + 1);
            }
        }
    }

    public void isCollision() {
        for (int i = 0; i < snake.getBodyParts().size(); i++) {
            if (snake.getBodyParts().get(i).getX() == snake.getX()
                    && snake.getBodyParts().get(i).getY() == snake.getY()) {
                setGameOver();
            }
        }
    }

    private boolean checkEat() {
        switch (dir) {
            case LEFT -> {
                if (snake.getX() == 1
                        && eat.getX() == WIDTH_GAME           //Проверка наличия еды, если зменя находится на крайней клетке поля
                        && eat.getY() == snake.getY()) {
                    return true;
                } else if (eat.getX() == snake.getX() - 1     //Проверка наличия еды на следующей клетке
                        && eat.getY() == snake.getY()) {
                    return true;
                }
            }
            case UP -> {
                if (snake.getY() == 1
                        && eat.getY() == HEIGHT_GAME
                        && eat.getX() == snake.getX()) {
                    return true;
                } else if (eat.getX() == snake.getX()
                        && eat.getY() == snake.getY() - 1) {
                    return true;
                }
            }
            case RIGHT -> {
                if (snake.getX() == WIDTH_GAME
                        && eat.getX() == 1
                        && eat.getY() == snake.getY()) {
                    return true;
                } else if (eat.getX() == snake.getX() + 1
                        && eat.getY() == snake.getY()) {
                    return true;
                }
            }
            case DOWN -> {
                if (snake.getY() == HEIGHT_GAME
                        && eat.getY() == 1
                        && eat.getX() == snake.getX()) {
                    return true;
                } else if (eat.getX() == snake.getX()
                        && eat.getY() == snake.getY() + 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void moveEat() {               //Перемещение еды в случайную точку в пределах игрового окна
        boolean isCorrectPoint = false;
        int randomX = 0;
        int randomY = 0;
        while (!isCorrectPoint) {          //Проверка на появление еды в теле змеи
            isCorrectPoint = true;
            randomX = getRandomInt(WIDTH_GAME);
            randomY = getRandomInt(HEIGHT_GAME);
            if (randomX == snake.getX() && randomY == snake.getY()) {
                isCorrectPoint = false;
                continue;
            }
            for (GameObject bodyPart : snake.getBodyParts()) {
                if (randomX == bodyPart.getX() && randomY == bodyPart.getY()) {
                    isCorrectPoint = false;
                    break;
                }
            }
        }
        eat.setX(randomX);
        eat.setY(randomY);
    }
    private int getRandomInt(int max) {
        return (int) (Math.random() * (max) +1);
    }

    private void saveRecord() {
        if (record < getSnake().getBodyParts().size()) {
            try (FileWriter writer = new FileWriter("src/resource/record.txt", false)) {
                String text = Integer.toString(getSnake().getBodyParts().size());
                writer.write(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int loadRecord() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resource/record.txt"))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void setGameOver() {
        this.gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public GameObject getEat() {
        return eat;
    }

    public int getRecord() {
        return record;
    }

    public Snake getSnake() {
        return snake;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Image getGameOverImg() {
        return gameOverImg;
    }

    public Image getHeadImg() {
        return headImg;
    }

    public Image getBodyImg() {
        return bodyImg;
    }

    public Image getEatImg() {
        return eatImg;
    }

}
