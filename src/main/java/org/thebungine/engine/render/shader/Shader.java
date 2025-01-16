package org.thebungine.engine.render.shader;

import org.joml.Matrix4f;

public abstract class Shader {

    protected Shader(String vertexShader, String fragmentShader) {

    }

    public abstract void bind();
    public abstract void unbind();
    public abstract void uploadUniformMat4(String name, Matrix4f data);

}
