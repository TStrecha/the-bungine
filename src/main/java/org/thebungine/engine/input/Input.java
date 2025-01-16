package org.thebungine.engine.input;

import org.joml.Vector2f;
import org.thebungine.engine.platform.windows.WindowsInput;

public abstract class Input {

    private static Input instance = null;

    public static Input getInstance() {
        if(instance == null) {
            instance = new WindowsInput();
        }

        return instance;
    }

    public abstract boolean isKeyPressed(KeyCode key);
    public abstract boolean isMouseKeyPressed(MouseButtonCode button);

    public abstract Vector2f getMousePos();
    public abstract float getMouseXPos();
    public abstract float getMouseYPos();
}
