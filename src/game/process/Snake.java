package game.process;

import settings.SettingConstant;

import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject {

    private final List<GameObject> bodyParts;
    public Snake(int x, int y) {
        super(x,y);
        bodyParts = new ArrayList<>();
        setStartBodyParts();
    }

    private void setStartBodyParts() {      //Добавление стартовых частей змеи
        for (int i = 1; i <= SettingConstant.START_BODY_PART; i++) {
            addBodyPart(new GameObject(getX() - (i), getY()));
        }
    }

    public List<GameObject> getBodyParts() {
        return bodyParts;
    }

    public void addBodyPart(GameObject bodyPart) {
        bodyParts.add(bodyPart);
    }

}
