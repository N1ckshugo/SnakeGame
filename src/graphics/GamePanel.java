package graphics;

import game.process.Game;
import game.process.GameObject;
import settings.Direction;
import settings.SettingConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static settings.SettingConstant.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Game game;
    private int x;
    private int y;

    public GamePanel(int delay) {
        setFocusable(true);
        addKeyListener(this);
        Timer timer = new Timer(delay, this);
        setFont(new Font("Callibri", Font.ITALIC, 24));
        game = new Game();
        timer.start();
    }

    public void starGame() {
        game = new Game();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.clearRect(0, 0, WIDTH_GAME_WINDOW, INDENT);                                //Очистка области
        g2.drawString("Скорость: " + 1 / (SettingConstant.DELAY / 1000.0) + " кл/сек", 5, 35);
        g2.drawString("Длинна: " + game.getSnake().getBodyParts().size(), 5, 65);
        if (game.getRecord() < game.getSnake().getBodyParts().size()) {
            g2.drawString("Рекорд: " + game.getSnake().getBodyParts().size(), WIDTH_GAME_WINDOW - 160, 65);
        } else {
            g2.drawString("Рекорд: " + game.getRecord(), WIDTH_GAME_WINDOW - 160, 65);
        }

        g2.clearRect(0, INDENT, WIDTH_GAME_WINDOW, HEIGHT_GAME_WINDOW);                   //Очистка области
        for (int i = 0; i <= WIDTH_GAME_WINDOW; i += CELL) {                                 //Отрисовка сетки
            g2.drawLine(i, INDENT, i, HEIGHT_GAME_WINDOW + INDENT);
        }
        for (int i = INDENT; i <= HEIGHT_GAME_WINDOW + INDENT; i += CELL) {
            g2.drawLine(0, i, WIDTH_GAME_WINDOW, i);
        }

        if (game.isGameOver()) {                                                             //отрисовка конец игры
            g2.drawImage(game.getGameOverImg(), INDENT + WIDTH_GAME_WINDOW / 2 - 141, INDENT + HEIGHT_GAME_WINDOW / 2 - 78, this);
        }
        g2.drawImage(game.getHeadImg(), game.getSnake().getGlobalX(), game.getSnake().getGlobalY(), this);  //Отрисовка головы
        for (GameObject bodyPart : game.getSnake().getBodyParts()) {                                                //Отрисовка тела
            g2.drawImage(game.getBodyImg(), bodyPart.getGlobalX(), bodyPart.getGlobalY(), this);
        }
        g2.drawImage(game.getEatImg(), game.getEat().getGlobalX(), game.getEat().getGlobalY(), this);       //Отрисовка еды
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x = -1;
        y = -1;
        repaint();
        if (!game.isGameOver()) {
            game.gameProgress();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!game.isGameOver()) {
            switch (e.getKeyCode()) {
                case 37:
                    if (game.getDir() != Direction.RIGHT && game.getSnake().getY() != y) {
                        game.setDir(Direction.LEFT);
                        x = game.getSnake().getX();
                        y = game.getSnake().getY();
                    }
                    break;
                case 38:
                    if (game.getDir() != Direction.DOWN && game.getSnake().getX() != x) {
                        game.setDir(Direction.UP);
                        x = game.getSnake().getX();
                        y = game.getSnake().getY();
                    }
                    break;
                case 39:
                    if (game.getDir() != Direction.LEFT && game.getSnake().getY() != y) {
                        game.setDir(Direction.RIGHT);
                        x = game.getSnake().getX();
                        y = game.getSnake().getY();
                    }
                    break;
                case 40:
                    if (game.getDir() != Direction.UP && game.getSnake().getX() != x) {
                        game.setDir(Direction.DOWN);
                        x = game.getSnake().getX();
                        y = game.getSnake().getY();
                    }
                    break;
            }
        }
        if (e.getKeyCode() == 32) {
            starGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
