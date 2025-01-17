package org.thebungine.engine.render;

import org.thebungine.engine.platform.opengl.OpenGLRendererAPI;
import org.thebungine.engine.render.buffer.VertexArray;

public abstract class RendererAPI {

    private static RendererAPI instance = null;

    public static RendererAPI getInstance() {
        if(instance == null) {
            instance = new OpenGLRendererAPI();
        }

        return instance;
    }

    public abstract void setClearColor(float red, float green, float blue, float alpha);

    public abstract void clear();

    public abstract void drawIndexed(VertexArray vertexArray);

    public abstract void init();
}
