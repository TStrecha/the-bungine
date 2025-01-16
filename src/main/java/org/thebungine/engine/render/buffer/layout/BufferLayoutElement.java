package org.thebungine.engine.render.buffer.layout;

import lombok.Data;

@Data
public final class BufferLayoutElement {

    private final ShaderDataType type;
    private final String name;
    private final boolean normalized;
    private int size;
    private int offset;

    public BufferLayoutElement(ShaderDataType type, String name, boolean normalized, int size, int offset) {
        this.type = type;
        this.name = name;
        this.normalized = normalized;
        this.size = size;
        this.offset = offset;
    }

    public BufferLayoutElement(ShaderDataType type, String name, boolean normalized) {
        this(type, name, normalized, type.getSize(), 0);
    }

    public BufferLayoutElement(ShaderDataType type, String name) {
        this(type, name, false, type.getSize(), 0);
    }

}
