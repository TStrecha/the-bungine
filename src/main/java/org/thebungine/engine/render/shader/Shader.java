package org.thebungine.engine.render.shader;

import org.joml.Matrix4f;
import org.joml.Vector4f;

public abstract class Shader {

    protected Shader(String vertexShader, String fragmentShader) {

    }

    public abstract void bind();
    public abstract void unbind();

    public abstract void uploadUniformMat4(String name, Matrix4f data);
    public abstract void uploadUniformVec4f(String name, Vector4f data);
}
