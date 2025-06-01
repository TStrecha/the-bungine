package org.thebungine.engine.render;

import org.thebungine.engine.core.BungineContext;
import org.thebungine.engine.render.buffer.VertexArray;

public abstract class RendererAPI {

    private static RendererAPI instance = null;

    public static RendererAPI getInstance() {
        if(instance == null) {
            instance = BungineContext.getInstance().getRendererFactory().instantiateRendererAPI();
        }

        return instance;
    }

    public abstract void setClearColor(float red, float green, float blue, float alpha);

    public abstract void clear();

    public abstract void setViewport(int x, int y, int width, int height);

    public abstract void drawIndexed(VertexArray vertexArray);

    public abstract void init();
}
