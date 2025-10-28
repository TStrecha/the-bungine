package org.thebungine.engine.render;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.thebungine.engine.core.BungineContext;
import org.thebungine.engine.render.buffer.VertexArray;
import org.thebungine.engine.render.buffer.layout.BufferLayout;
import org.thebungine.engine.render.buffer.layout.BufferLayoutElement;
import org.thebungine.engine.render.buffer.layout.ShaderDataType;
import org.thebungine.engine.render.camera.OrthographicCamera;
import org.thebungine.engine.render.shader.Shader;
import org.thebungine.engine.render.shader.ShaderLibrary;
import org.thebungine.engine.render.texture.Texture;

import java.util.List;

public class Renderer2D {

    private static final VertexArray quadVertexArray;
    private static final Shader shader;
    private static final Texture whiteTexture;

    static {
        var squareVertices = new float[] {
                -0.7f, -0.7f, 0.0f, 0.0f, 0.0f,
                0.7f, -0.7f, 0.0f, 1.0f, 0.0f,
                0.7f,  0.7f, 0.0f, 1.0f, 1.0f,
                -0.7f, 0.7f, 0.0f, 0.0f, 1.0f,
        };

        var squareIndices = new int[] { 0, 1, 2, 2, 3, 0};

        var squareBufferLayoutElements = List.of(
                new BufferLayoutElement(ShaderDataType.FLOAT_3, "aPos"),
                new BufferLayoutElement(ShaderDataType.FLOAT_2, "aTextureCoordinate")
        );
        var squareBufferLayout = new BufferLayout(squareBufferLayoutElements);

        var squareVertexBuffer = BungineContext.getInstance().getRendererFactory().createVertexBuffer(squareVertices);
        squareVertexBuffer.setBufferLayout(squareBufferLayout);

        var squareIndexBuffer = BungineContext.getInstance().getRendererFactory().createIndexBuffer(squareIndices);

        quadVertexArray = BungineContext.getInstance().getRendererFactory().createVertexArray();
        quadVertexArray.addVertexBuffer(squareVertexBuffer);
        quadVertexArray.setIndexBuffer(squareIndexBuffer);

        whiteTexture = BungineContext.getInstance().getRendererFactory().createTexture2D(1, 1, 4);
        whiteTexture.setData(new int[]{0xFFFFFFFF});

        try {
            shader = ShaderLibrary.load(Renderer2D.class.getResource("/shader/texture_shader.glsl"));
            shader.bind();
            shader.setInt("uTexture", 0);
        } catch (Exception e) {
            throw new RuntimeException(e);  // todo
        }
    }

    public static void beginScene(OrthographicCamera camera) {
        camera.recalculateViewMatrix();

        shader.setMat4("uViewProjection", camera.getViewProjection());
    }

    public static void endScene() {

    }

    public static void drawQuad(Vector3f pos, Vector2f size, Vector4f color) {
        whiteTexture.bind();
        shader.setFloat4("uColor", color);
        shader.setMat4("uTransform", new Matrix4f().translate(pos).scale(new Vector3f(size, 1)));

        quadVertexArray.bind();
        RenderCommand.drawIndexed(quadVertexArray);
    }

    public static void drawQuad(Vector3f pos, Vector2f size, Texture texture) {
        texture.bind();
        shader.setFloat4("uColor", new Vector4f(1.0f, 1.0f, 1.0f, 1.0f));
        shader.setMat4("uTransform", new Matrix4f().translate(pos).scale(new Vector3f(size, 1)));

        quadVertexArray.bind();
        RenderCommand.drawIndexed(quadVertexArray);
    }

    public static void drawQuad(Vector2f pos, Vector2f size, Vector4f color) {
        drawQuad(new Vector3f(pos, 0.0f), size, color);
    }

    public static void drawQuad(Vector2f pos, Vector2f size, Texture texture) {
        drawQuad(new Vector3f(pos, 0.0f), size, texture);
    }
}
