package org.thebungine.engine.render.texture;

public interface Texture {

    int getWidth();

    int getHeight();

    void bind(int slot);

    void setData(int[] buffer);

    default void bind() {
        bind(0);
    }
}
