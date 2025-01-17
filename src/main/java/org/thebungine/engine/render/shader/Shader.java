package org.thebungine.engine.render.shader;

import org.joml.Matrix4f;
import org.joml.Vector4f;

public abstract class Shader {

    protected Shader(String vertexShader, String fragmentShader) {

    }

    public abstract void bind();
    public abstract void unbind();
}
