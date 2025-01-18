package org.thebungine.engine.core;

import org.thebungine.engine.input.Input;
import org.thebungine.engine.platform.opengl.factory.OpenGLFactory;
import org.thebungine.engine.render.Renderer;
import org.thebungine.engine.render.RendererAPI;
import org.thebungine.engine.render.RendererType;
import org.thebungine.engine.render.buffer.IndexBuffer;
import org.thebungine.engine.render.buffer.VertexArray;
import org.thebungine.engine.render.buffer.VertexBuffer;
import org.thebungine.engine.render.shader.Shader;
import org.thebungine.engine.render.texture.Texture2D;
import org.thebungine.engine.window.Window;
import org.thebungine.engine.window.WindowProperties;

import java.io.IOException;

public interface RendererFactory {

    Window createWindow(WindowProperties windowProperties);
    Shader createShader(String vertexShader, String fragmentShader);
    VertexBuffer createVertexBuffer(float[] vertices);
    IndexBuffer createIndexBuffer(int[] indices);
    VertexArray createVertexArray();
    Texture2D createTexture2D(String path) throws IOException;

    RendererAPI instantiateRendererAPI();
    Input instantiateInput();

    RendererType getRendererType();

    static RendererFactory instantiate(RendererType rendererType) {
        return switch (Renderer.getRendererType()) {
            case OPENGL -> new OpenGLFactory();
        };
    }
}
