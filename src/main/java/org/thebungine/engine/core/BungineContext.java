package org.thebungine.engine.core;

import lombok.Getter;
import org.thebungine.engine.render.RendererType;

@Getter
public class BungineContext {

    private static BungineContext instance = null;

    private final RendererType rendererType;
    private final RendererFactory rendererFactory;

    private BungineContext() {
        this.rendererType = RendererType.OPENGL;
        this.rendererFactory = RendererFactory.instantiate(this.rendererType);
    }

    public static BungineContext getInstance() {
        if(instance == null) {
            instance = new BungineContext();
        }

        return instance;
    }
}
