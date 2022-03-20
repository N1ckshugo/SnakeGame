package settings;

public class SettingConstant {

    public static final int DELAY = 50;                                  //количество милисекунд между кадрами
    public static final int INDENT = 80;                                 //отступ игрового окна от 0 по Y
    public static final int HEIGHT_GAME_WINDOW = 800;                    //высота игрового поля(Должно быть кратно CELL)
    public static final int WIDTH_GAME_WINDOW = 1000;                    //ширина игрового поля(Должно быть кратно CELL)
    public static final int CELL = 20;                                   //Размер клетки, змеи, еды в пикселях
    public static final int START_BODY_PART = 6;                         //начальное количество частей змеи
    public static final Direction START_DIRECTION = Direction.RIGHT;     //начальное направление движения

    public static final int HEIGHT_GAME = HEIGHT_GAME_WINDOW / CELL;     //высота игрового игрового поля по количеству ячеек
    public static final int WIDTH_GAME = WIDTH_GAME_WINDOW / CELL;       //ширина игрового игрового поля по количеству ячеек
    public static final int START_SNAKE_X = WIDTH_GAME;                  //начальное положение змени по X
    public static final int START_SNAKE_Y = HEIGHT_GAME / 2;             //начальное положение змени по Y

    public static final int HEIGHT = HEIGHT_GAME_WINDOW + INDENT + 40;   //высота окна
    public static final int WIDHT = WIDTH_GAME_WINDOW + 17;              //ширина окна

}
