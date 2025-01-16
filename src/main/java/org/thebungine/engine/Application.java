package org.thebungine.engine;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.thebungine.engine.event.Event;
import org.thebungine.engine.event.EventDispatcher;
import org.thebungine.engine.event.WindowCloseEvent;
import org.thebungine.engine.layer.ImguiLayer;
import org.thebungine.engine.layer.Layer;
import org.thebungine.engine.layer.LayerStack;
import org.thebungine.engine.util.TimeStep;
import org.thebungine.engine.window.Window;
import lombok.Setter;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

@Slf4j
public abstract class Application {

    @Getter
    private static Application instance = null;

    private float lastFrameTime = 0;


    private final LayerStack layerStack = new LayerStack();
    private final ImguiLayer imguiLayer = new ImguiLayer();
    private Boolean running = true;

    @Getter
    @Setter
    private Window window;

    protected Application() {
        instance = this;

        EventDispatcher.getInstance().registerGeneralListener(this::onEvent);

        EventDispatcher.getInstance().registerListener(WindowCloseEvent.class, event -> running = false);
    }

    public void pushLayer(Layer layer) {
        layerStack.pushLayer(layer);
    }

    public void pushOverlay(Layer overlay) {
        layerStack.pushOverlay(overlay);
    }

    public void popLayer(Layer layer) {
        layerStack.popLayer(layer);
    }

    public void popOverlay(Layer overlay) {
        layerStack.popOverlay(overlay);
    }

    public void onEvent(Event event) {
        for (int i = layerStack.getLayers().size() - 1; i >= 0; i--) {
            layerStack.getLayer(i).onEvent(event);
        }
    }

    public void run() {
        imguiLayer.onAttach();

        while(running) {
            var time = (float) glfwGetTime();
            var timeStep = new TimeStep(time - lastFrameTime);
            this.lastFrameTime = time;

            imguiLayer.onUpdate(timeStep);
            this.layerStack.getLayers().forEach(layer -> layer.onUpdate(timeStep));

            imguiLayer.beginRender();
            this.layerStack.getLayers().forEach(layer -> layer.renderImGui(timeStep));
            imguiLayer.renderImGui(timeStep);
            imguiLayer.endRender();

            window.onUpdate();
        }

        window.destroy();
        layerStack.getLayers().forEach(Layer::onDeAttach);
    }
}

