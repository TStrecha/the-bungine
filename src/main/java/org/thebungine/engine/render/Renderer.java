package org.thebungine.engine.render;

public class Renderer {

    private static Scene currentScene = null;

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
