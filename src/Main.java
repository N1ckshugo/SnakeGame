import graphics.GameFrame;
import graphics.GamePanel;
import settings.SettingConstant;

public class Main {
    public static void main(String[] args) {
        GameFrame frame = new GameFrame(SettingConstant.WIDHT, SettingConstant.HEIGHT, true);
        frame.add(new GamePanel(SettingConstant.DELAY));
        frame.configure();
    }
}
