package org.thebungine.engine.platform.opengl;

import org.thebungine.engine.event.EventDispatcher;
import org.thebungine.engine.event.MouseScrollEvent;
import org.thebungine.engine.event.WindowCloseEvent;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.thebungine.engine.event.WindowResizeEvent;
import org.thebungine.engine.window.Window;
import org.thebungine.engine.window.WindowProperties;

import static org.lwjgl.system.MemoryUtil.NULL;

@SuppressWarnings("unused")
public class OpenGLWindow implements Window {

    private long windowPointer;

    private boolean vSync;

    public OpenGLWindow(WindowProperties properties) {
        init(properties);
    }

    @Override
    public void init(WindowProperties properties) {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Couldn't initiate GLFW.");
        }

        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 1);
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);

        this.windowPointer = GLFW.glfwCreateWindow(properties.getWidth(), properties.getHeight(), properties.getTitle(), NULL, NULL);

        if (getWindowPointer() == NULL) {
            throw new IllegalStateException("Couldn't initiate window.");
        }

        try(var previousCallback = GLFW.glfwSetWindowCloseCallback(getWindowPointer(), windowId ->
                EventDispatcher.getInstance().dispatchEvent(new WindowCloseEvent(windowId)))) {
        }

        try(var previousCallback = GLFW.glfwSetScrollCallback(getWindowPointer(), (windowId, xOffset, yOffset) ->
                EventDispatcher.getInstance().dispatchEvent(new MouseScrollEvent((float) xOffset, (float) yOffset)))) {
        }

        try(var previousCallback = GLFW.glfwSetWindowSizeCallback(getWindowPointer(), (windowId, width, height) ->
                EventDispatcher.getInstance().dispatchEvent(new WindowResizeEvent(width, height)))) {
        }

        GLFW.glfwMakeContextCurrent(getWindowPointer());
        GLFW.glfwShowWindow(getWindowPointer());
        GL.createCapabilities();

        setVSync(true);
    }

    @Override
    public void destroy() {
        Callbacks.glfwFreeCallbacks(getWindowPointer());
        GLFW.glfwDestroyWindow(getWindowPointer());
        GLFW.glfwTerminate();
    }

    @Override
    public void onUpdate() {
        GLFW.glfwSwapBuffers(getWindowPointer());
        GLFW.glfwPollEvents();
    }

    @Override
    public boolean isVSync() {
        return vSync;
    }

    @Override
    public void setVSync(boolean vSync) {
        if(vSync) {
            GLFW.glfwSwapInterval(1);

        } else {
            GLFW.glfwSwapInterval(0);
        }

        this.vSync = vSync;
    }

    @Override
    public Long getWindowId() {
        return getWindowPointer();
    }

    public long getWindowPointer() {
        if(windowPointer == 0) {
            throw new IllegalStateException("Window has not been initialized yet. WindowPointer: " + windowPointer);
        }

        return windowPointer;
    }
}
