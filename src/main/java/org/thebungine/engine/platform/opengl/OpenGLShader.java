package org.thebungine.engine.platform.opengl;

import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;
import org.thebungine.engine.render.shader.Shader;

public class OpenGLShader extends Shader {

    private final int programId;

    public OpenGLShader(String vertexShader, String fragmentShader) {
        super(vertexShader, fragmentShader);

        int vertexShaderId = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexShaderId, vertexShader);
        GL20.glCompileShader(vertexShaderId);
        checkShaderCompileStatus(vertexShaderId);

        int fragmentShaderId = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentShaderId, fragmentShader);
        GL20.glCompileShader(fragmentShaderId);
        checkShaderCompileStatus(fragmentShaderId);

        this.programId = GL20.glCreateProgram();
        GL20.glAttachShader(this.programId, vertexShaderId);
        GL20.glAttachShader(this.programId, fragmentShaderId);
        GL20.glLinkProgram(this.programId);
        checkProgramLinkStatus(this.programId);

        GL20.glDeleteShader(vertexShaderId);
        GL20.glDeleteShader(fragmentShaderId);
    }

    private void checkShaderCompileStatus(int shader) {
        if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            throw new IllegalArgumentException("Shader compile error: " + GL20.glGetShaderInfoLog(shader));
        }
    }

    private void checkProgramLinkStatus(int program) {
        if (GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            throw new IllegalArgumentException("Program link error: " + GL20.glGetProgramInfoLog(program));
        }
    }

    public void uploadUniformInt(String name, int data) {
        GL20.glUseProgram(programId);

        var location = GL20.glGetUniformLocation(programId, name);
        GL20.glUniform1i(location, data);
    }

    public void uploadUniformMat4(String name, Matrix4f data) {
        GL20.glUseProgram(programId);

        var location = GL20.glGetUniformLocation(programId, name);
        try (MemoryStack stack = MemoryStack.stackPush()) {
            var buffer = data.get(stack.mallocFloat(16));

            GL20.glUniformMatrix4fv(location, false, buffer);
        }
    }

    public void uploadUniformVec4f(String name, Vector4f data) {
        GL20.glUseProgram(programId);

        var location = GL20.glGetUniformLocation(programId, name);
        GL20.glUniform4f(location, data.x, data.y, data.z, data.w);
    }

    @Override
    public void bind() {
        GL20.glUseProgram(programId);
    }

    @Override
    public void unbind() {
        GL20.glUseProgram(0);
    }
}
