package org.thebungine.engine.layer;

import org.thebungine.engine.util.TimeStep;
import org.thebungine.engine.event.Event;

public abstract class Layer {

    @SuppressWarnings("unused")
    public String getName() {
        return "Layer";
    }

    public void onAttach() {}
    public void onDeAttach() {}

    public abstract void onUpdate(TimeStep timeStep);

    public void onEvent(Event event) {}
}
