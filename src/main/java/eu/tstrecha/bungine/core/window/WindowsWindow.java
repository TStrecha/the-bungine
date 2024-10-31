package eu.tstrecha.bungine.core.window;

import eu.tstrecha.bungine.core.event.EventDispatcher;
import eu.tstrecha.bungine.core.event.WindowCloseEvent;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetWindowCloseCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.system.MemoryUtil.NULL;

public class WindowsWindow implements Window {

    private long windowPointer;

    public WindowsWindow(WindowProperties properties) {
        init(properties);
    }

    @Override
    public void init(WindowProperties properties) {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Couldn't initiate GLFW.");
        }

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        this.windowPointer = glfwCreateWindow(properties.getWidth(), properties.getHeight(), properties.getTitle(), NULL, NULL);

        if (getWindowPointer() == NULL) {
            throw new IllegalStateException("Couldn't initiate window.");
        }

        var previousCallback = glfwSetWindowCloseCallback(getWindowPointer(), (windowId) ->
                EventDispatcher.getInstance().dispatchEvent(new WindowCloseEvent(windowId)));
        if(previousCallback != null) {
            previousCallback.close();
        }

        glfwMakeContextCurrent(getWindowPointer());
        glfwSwapInterval(1);
        glfwShowWindow(getWindowPointer());
        GL.createCapabilities();
    }

    @Override
    public void destroy() {
        Callbacks.glfwFreeCallbacks(getWindowPointer());
        glfwDestroyWindow(getWindowPointer());
        glfwTerminate();
    }

    @Override
    public void onUpdate() {
        GLFW.glfwSwapBuffers(getWindowPointer());
        GLFW.glfwPollEvents();
    }

    public long getWindowPointer() {
        if(windowPointer == 0) {
            throw new IllegalStateException("Window has not been initialized yet. WindowPointer: " + windowPointer);
        }

        return windowPointer;
    }
}
