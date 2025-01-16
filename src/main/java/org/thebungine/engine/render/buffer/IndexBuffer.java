package org.thebungine.engine.render.buffer;

import lombok.Getter;

@Getter
public abstract class IndexBuffer {

    protected final int count;

    public IndexBuffer(int[] indices) {
        this.count = indices.length;
    }

    public abstract void bind();

    public abstract void unbind();
}
