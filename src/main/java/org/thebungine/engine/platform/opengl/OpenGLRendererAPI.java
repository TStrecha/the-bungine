package org.thebungine.engine.platform.opengl;

import org.lwjgl.opengl.GL11;
import org.thebungine.engine.render.RendererAPI;
import org.thebungine.engine.render.buffer.VertexArray;

public class OpenGLRendererAPI extends RendererAPI {

    @Override
    public void setClearColor(float red, float green, float blue, float alpha) {
        GL11.glClearColor(red, green, blue, alpha);
    }

    @Override
    public void setViewport(int x, int y, int width, int height) {
        GL11.glViewport(x, y, width, height);
    }

    @Override
    public void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void drawIndexed(VertexArray vertexArray) {
        GL11.glDrawElements(GL11.GL_TRIANGLES, vertexArray.getIndexBuffer().getCount(), GL11.GL_UNSIGNED_INT, 0);
    }

    @Override
    public void init() {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }
}
