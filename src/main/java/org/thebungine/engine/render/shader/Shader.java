package org.thebungine.engine.render.shader;

public abstract class Shader {

    protected Shader(String vertexShader, String fragmentShader) {

    }

    public abstract void bind();
    public abstract void unbind();
}
