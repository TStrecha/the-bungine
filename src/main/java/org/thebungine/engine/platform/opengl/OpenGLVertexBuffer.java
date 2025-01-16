package org.thebungine.engine.platform.opengl;

import lombok.Getter;
import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryUtil;
import org.thebungine.engine.render.buffer.VertexBuffer;

@Getter
public class OpenGLVertexBuffer extends VertexBuffer {

    private final int vertexBufferId;

    public OpenGLVertexBuffer(float[] vertices) {
        super(vertices);

        this.vertexBufferId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferId);

        var vertexBuffer = MemoryUtil.memAllocFloat(vertices.length).put(vertices).flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBuffer, GL15.GL_STATIC_DRAW);
    }

    @Override
    public void bind() {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.vertexBufferId);
    }

    @Override
    public void unbind() {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
}
