package eu.tstrecha.bungine.core.layer;

import eu.tstrecha.bungine.core.event.Event;

public abstract class Layer {

    public String getName() {
        return "Layer";
    }

    public void onAttach() {}
    public void onDeAttach() {}

    public abstract void onUpdate();

    public void onEvent(Event event) {}
}
