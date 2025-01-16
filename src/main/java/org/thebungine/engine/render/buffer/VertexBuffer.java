package org.thebungine.engine.render.buffer;

import lombok.Getter;
import lombok.Setter;
import org.thebungine.engine.render.buffer.layout.BufferLayout;

@Setter
@Getter
public abstract class VertexBuffer {

    private BufferLayout bufferLayout;

    public VertexBuffer(float[] vertices) {}

    public abstract void bind();

    public abstract void unbind();

}
