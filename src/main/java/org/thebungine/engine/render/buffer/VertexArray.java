package org.thebungine.engine.render.buffer;

public abstract class VertexArray {

    public abstract void addVertexBuffer(VertexBuffer vertexBuffer);
    public abstract void setIndexBuffer(IndexBuffer indexBuffer);
    public abstract IndexBuffer getIndexBuffer();

    public abstract void bind();

    public abstract void unbind();
}
