package org.thebungine.engine.platform.windows;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.thebungine.engine.Application;
import org.thebungine.engine.input.Input;
import org.thebungine.engine.input.KeyCode;
import org.thebungine.engine.input.MouseButtonCode;

import java.nio.DoubleBuffer;

public class WindowsInput extends Input {

    @Override
    public boolean isKeyPressed(KeyCode key) {
        var state = GLFW.glfwGetKey(Application.getInstance().getWindow().getWindowId(), key.getValue());
        return state == GLFW.GLFW_PRESS || state == GLFW.GLFW_REPEAT;
    }

    @Override
    public boolean isMouseKeyPressed(MouseButtonCode button) {
        var state = GLFW.glfwGetMouseButton(Application.getInstance().getWindow().getWindowId(), button.getValue());
        return state == GLFW.GLFW_PRESS;
    }

    @Override
    public Vector2f getMousePos() {
        var x = DoubleBuffer.allocate(1);
        var y = DoubleBuffer.allocate(1);

        GLFW.glfwGetCursorPos(Application.getInstance().getWindow().getWindowId(), x, y);

        return new Vector2f((float) x.get(), (float) y.get());
    }

    @Override
    public float getMouseXPos() {
        return getMousePos().x;
    }

    @Override
    public float getMouseYPos() {
        return getMousePos().y;
    }
}
