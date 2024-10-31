package org.thebungine.engine;

import org.thebungine.engine.event.Event;
import org.thebungine.engine.event.EventDispatcher;
import org.thebungine.engine.event.WindowCloseEvent;
import org.thebungine.engine.layer.Layer;
import org.thebungine.engine.layer.LayerStack;
import org.thebungine.engine.window.Window;
import lombok.Setter;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

public abstract class Application {

    private final LayerStack layerStack = new LayerStack();
    private Boolean running = true;

    @Setter
    private Window window;

    public Application() {
        EventDispatcher.getInstance().registerGeneralListener(this::onEvent);

        EventDispatcher.getInstance().registerListener(WindowCloseEvent.class, (event) -> running = false);
    }

    public void onEvent(Event event) {
        for (int i = layerStack.getLayers().size() - 1; i >= 0; i--) {
            layerStack.getLayer(i).onEvent(event);
        }
    }

    public void run() {
        while(running) {
            glClearColor(0.12f, 0.1f, 0.12f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            this.layerStack.getLayers().forEach(Layer::onUpdate);
            window.onUpdate();
        }

        window.destroy();
    }
}
