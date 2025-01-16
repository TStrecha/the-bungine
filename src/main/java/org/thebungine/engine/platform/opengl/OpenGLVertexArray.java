package org.thebungine.engine.platform.opengl;

import lombok.Getter;
import lombok.Setter;
import org.thebungine.engine.render.buffer.IndexBuffer;
import org.thebungine.engine.render.buffer.VertexArray;
import org.thebungine.engine.render.buffer.VertexBuffer;
import org.thebungine.engine.render.buffer.layout.ShaderDataType;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_INT;
import static org.lwjgl.opengl.GL20.GL_BOOL;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class OpenGLVertexArray extends VertexArray {

    @Getter
    private final int vertexArrayId;

    @Getter
    private IndexBuffer indexBuffer;
    private final List<VertexBuffer> vertexBuffers;

    public OpenGLVertexArray() {
        this.vertexBuffers = new ArrayList<>();
        this.vertexArrayId = glGenVertexArrays();
    }

    @Override
    public void addVertexBuffer(VertexBuffer vertexBuffer) {
        glBindVertexArray(vertexArrayId);
        vertexBuffer.bind();

        var bufferLayout = vertexBuffer.getBufferLayout();

        for (int i = 0; i < bufferLayout.getElements().size(); i++) {
            var element = bufferLayout.getElements().get(i);

            glEnableVertexAttribArray(i);
            glVertexAttribPointer(i, element.getType().getElementCount(), getGLType(element.getType()), element.isNormalized(), bufferLayout.getStride(), element.getOffset());
        }

        vertexBuffers.add(vertexBuffer);
    }

    public void setIndexBuffer(IndexBuffer indexBuffer) {
        glBindVertexArray(vertexArrayId);
        indexBuffer.bind();

        this.indexBuffer = indexBuffer;
    }

    @Override
    public void bind() {
        glBindVertexArray(vertexArrayId);
    }

    @Override
    public void unbind() {
        glBindVertexArray(0);
    }

    private int getGLType(ShaderDataType shaderType) {
        return switch (shaderType) {
            case FLOAT, FLOAT_2, FLOAT_3, FLOAT_4, MAT_3, MAT_4 -> GL_FLOAT;
            case INT, INT_2, INT_3, INT_4 -> GL_INT;
            case BOOL -> GL_BOOL;
        };
    }

}
