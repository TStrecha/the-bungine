package org.thebungine.engine.render.camera;

import lombok.Getter;
import lombok.Setter;
import org.joml.Vector3f;
import org.thebungine.engine.event.EventDispatcher;
import org.thebungine.engine.event.MouseScrollEvent;
import org.thebungine.engine.event.WindowResizeEvent;
import org.thebungine.engine.input.Input;
import org.thebungine.engine.input.KeyCode;
import org.thebungine.engine.util.TimeStep;

@Getter
@Setter
public class OrthographicCameraController {

    private final OrthographicCamera camera;

    private boolean rotationEnabled;
    private boolean resetEnabled;
    private float aspectRatio;

    private float translationSpeed = 5.0f;
    private float rotationSpeed = 180f;

    private float zoomLevel = 1;

    public OrthographicCameraController(float aspectRatio, boolean rotationEnabled, boolean resetEnabled) {
        this.rotationEnabled = rotationEnabled;
        this.resetEnabled = resetEnabled;
        this.aspectRatio = aspectRatio;

        this.camera = new OrthographicCamera(-aspectRatio * zoomLevel, aspectRatio * zoomLevel, -zoomLevel, zoomLevel);

        EventDispatcher.getInstance().registerListener(MouseScrollEvent.class, this::onMouseScrollEvent);
        EventDispatcher.getInstance().registerListener(WindowResizeEvent.class, this::onWindowResizeEvent);
    }

    public void onUpdate(TimeStep timeStep) {
        if(Input.getInstance().isKeyPressed(KeyCode.KEY_W)) {
            camera.getPosition().add(0.0f, translationSpeed * timeStep.getSeconds(), 0.0f);
        }
        if(Input.getInstance().isKeyPressed(KeyCode.KEY_S)) {
            camera.getPosition().add(0.0f, -translationSpeed * timeStep.getSeconds(), 0.0f);

        }
        if(Input.getInstance().isKeyPressed(KeyCode.KEY_A)) {
            camera.getPosition().add(-translationSpeed * timeStep.getSeconds(), 0.0f, 0.0f);

        }
        if(Input.getInstance().isKeyPressed(KeyCode.KEY_D)) {
            camera.getPosition().add(translationSpeed * timeStep.getSeconds(), 0.0f, 0.0f);
        }

        if(rotationEnabled) {
            if(Input.getInstance().isKeyPressed(KeyCode.KEY_Q)) {
                camera.setRotation(camera.getRotation() - (rotationSpeed * timeStep.getSeconds()));
            }
            if(Input.getInstance().isKeyPressed(KeyCode.KEY_E)) {
                camera.setRotation(camera.getRotation() + (rotationSpeed * timeStep.getSeconds()));
            }
        }

        if(resetEnabled) {
            if(Input.getInstance().isKeyPressed(KeyCode.KEY_R)) {
                camera.setRotation(0.0f);
                camera.setPosition(new Vector3f(0.0f));
            }
        }
    }

    public void onMouseScrollEvent(MouseScrollEvent event) {
        zoomLevel -= event.getYOffset() / 5.0f;
        camera.setProjection(-aspectRatio * zoomLevel, aspectRatio * zoomLevel, -zoomLevel, zoomLevel);
    }

    public void onWindowResizeEvent(WindowResizeEvent event) {
        aspectRatio = (float) event.getWidth() / event.getHeight();
        camera.setProjection(-aspectRatio * zoomLevel, aspectRatio * zoomLevel, -zoomLevel, zoomLevel);
    }
}
