package org.thebungine.engine.layer;

import org.thebungine.engine.event.Event;

public abstract class Layer {

    public String getName() {
        return "Layer";
    }

    public void onAttach() {}
    public void onDeAttach() {}

    public abstract void onUpdate();

    public void onEvent(Event event) {}
}
