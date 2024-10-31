package eu.tstrecha.bungine.core.window;

public interface Window {

    void init(WindowProperties properties);

    void destroy();

    void onUpdate();
}
