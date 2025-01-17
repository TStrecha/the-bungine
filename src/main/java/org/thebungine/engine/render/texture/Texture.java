package org.thebungine.engine.render.texture;

public interface Texture {

    int getWidth();

    int getHeight();

    void bind(int slot);

    default void bind() {
        bind(0);
    }
}
