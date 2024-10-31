package org.thebungine.engine.window;

public interface Window {

    void init(WindowProperties properties);

    void destroy();

    void onUpdate();
}
