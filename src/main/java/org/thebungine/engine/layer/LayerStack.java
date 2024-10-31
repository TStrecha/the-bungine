package org.thebungine.engine.layer;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class LayerStack {

    @Getter
    private final List<Layer> layers = new LinkedList<>();
    private int layerIndex = 0;

    public void pushLayer(Layer layer) {
        this.layers.add(layerIndex, layer);
        layerIndex++;

        layer.onAttach();
    }

    public void pushOverlay(Layer overlay) {
        this.layers.add(overlay);
        overlay.onAttach();
    }

    public void popLayer(Layer layer) {
        this.layers.remove(layer);
        layerIndex--;

        layer.onDeAttach();
    }

    public void popOverlay(Layer overlay) {
        this.layers.remove(overlay);
        overlay.onDeAttach();
    }

    public Layer getLayer(int i) {
        return layers.get(i);
    }
}
