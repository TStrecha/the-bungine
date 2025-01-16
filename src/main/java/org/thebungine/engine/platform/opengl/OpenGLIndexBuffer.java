package org.thebungine.engine.platform.opengl;

import lombok.Getter;
import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryUtil;
import org.thebungine.engine.render.buffer.IndexBuffer;

@Getter
public class OpenGLIndexBuffer extends IndexBuffer {

    private final int indexBufferId;

    public OpenGLIndexBuffer(int[] indices) {
        super(indices);

        this.indexBufferId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBufferId);

        var indexBuffer = MemoryUtil.memAllocInt(indices.length).put(indices).flip();
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL15.GL_STATIC_DRAW);
    }

    @Override
    public void bind() {
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.indexBufferId);
    }

    @Override

    public void unbind() {
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }
}
