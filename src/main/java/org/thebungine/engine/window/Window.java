package org.thebungine.engine.window;

public interface Window {

    Long getWindowId();

    void init(WindowProperties properties);

    void destroy();

    void onUpdate();

    boolean isVSync();
    void setVSync(boolean vSync);
}
