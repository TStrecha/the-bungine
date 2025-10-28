package org.thebungine.engine.render.shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public abstract class Shader {

    protected Shader(String vertexShader, String fragmentShader) {

    }

    public abstract void bind();
    public abstract void unbind();

    public abstract void setInt(String name, Integer data);
    public abstract void setMat4(String name, Matrix4f data);
    public abstract void setFloat3(String name,  Vector3f data);
    public abstract void setFloat4(String name, Vector4f data);
}
