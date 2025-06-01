package org.thebungine.engine.render.camera;

import lombok.Getter;
import lombok.Setter;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@Getter
public class OrthographicCamera {

    @Setter
    private Matrix4f projection;
    private final Matrix4f view = new Matrix4f();
    private final Matrix4f viewProjection = new Matrix4f();

    @Setter
    private Vector3f position = new Vector3f(0f, 0f, 0f);
    @Setter
    private float rotation = 0f;

    public OrthographicCamera(float left, float right, float bottom, float top) {
        projection = new Matrix4f().ortho(left, right, bottom, top, -1.0f, 1.0f);
    }

    public void recalculateViewMatrix() {
        var transform = new Matrix4f()
                .translate(position)
                .rotate((float) Math.toRadians(rotation), 0.0f, 0.0f, 1.0f);

        transform.invert(view);
        projection.mul(view, viewProjection);
    }

    public void setProjection(float left, float right, float bottom, float top) {
        this.projection = new Matrix4f().ortho(left, right, bottom, top, -1.0f, 1.0f);
    }
}
