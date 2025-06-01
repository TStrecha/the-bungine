package org.thebungine.engine.render;

import lombok.Getter;

public class Renderer {

    @Getter
    private static final RendererType rendererType = RendererType.OPENGL;

    private static Scene currentScene = null;

    public static void init() {
        RenderCommand.init();
    }

    public static void onWindowResize(int width, int height) {
        RenderCommand.setViewport(0, 0, width, height);
    }

    public static Scene beginScene() {
        currentScene = new Scene();

        return currentScene;
    }

    public static void endScene() {
        if(currentScene == null) {
            throw new IllegalStateException("No scene is currently running.");
        }

        currentScene.end();
    }
}
