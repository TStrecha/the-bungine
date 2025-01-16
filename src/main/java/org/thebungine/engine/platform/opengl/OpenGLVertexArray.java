package org.thebungine.engine.platform.opengl;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.thebungine.engine.render.buffer.IndexBuffer;
import org.thebungine.engine.render.buffer.VertexArray;
import org.thebungine.engine.render.buffer.VertexBuffer;
import org.thebungine.engine.render.buffer.layout.ShaderDataType;

import java.util.ArrayList;
import java.util.List;

public class OpenGLVertexArray extends VertexArray {

    @Getter
    private final int vertexArrayId;

    @Getter
    private IndexBuffer indexBuffer;
    private final List<VertexBuffer> vertexBuffers;

    public OpenGLVertexArray() {
        this.vertexBuffers = new ArrayList<>();
        this.vertexArrayId = GL30.glGenVertexArrays();
    }

    @Override
    public void addVertexBuffer(VertexBuffer vertexBuffer) {
        GL30.glBindVertexArray(vertexArrayId);
        vertexBuffer.bind();

        var bufferLayout = vertexBuffer.getBufferLayout();

        for (int i = 0; i < bufferLayout.getElements().size(); i++) {
            var element = bufferLayout.getElements().get(i);

            GL20.glEnableVertexAttribArray(i);
            GL20.glVertexAttribPointer(i, element.getType().getElementCount(), getGLType(element.getType()), element.isNormalized(), bufferLayout.getStride(), element.getOffset());
        }

        vertexBuffers.add(vertexBuffer);
    }

    public void setIndexBuffer(IndexBuffer indexBuffer) {
        GL30.glBindVertexArray(vertexArrayId);
        indexBuffer.bind();

        this.indexBuffer = indexBuffer;
    }

    @Override
    public void bind() {
        GL30.glBindVertexArray(vertexArrayId);
    }

    @Override
    public void unbind() {
        GL30.glBindVertexArray(0);
    }

    private int getGLType(ShaderDataType shaderType) {
        return switch (shaderType) {
            case FLOAT, FLOAT_2, FLOAT_3, FLOAT_4, MAT_3, MAT_4 -> GL11.GL_FLOAT;
            case INT, INT_2, INT_3, INT_4 -> GL11.GL_INT;
            case BOOL -> GL20.GL_BOOL;
        };
    }

}
