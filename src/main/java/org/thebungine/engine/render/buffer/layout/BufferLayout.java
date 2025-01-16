package org.thebungine.engine.render.buffer.layout;

import lombok.Getter;

import java.util.List;

@Getter
public class BufferLayout {

    private final List<BufferLayoutElement> elements;
    private int stride;

    public BufferLayout(List<BufferLayoutElement> elements) {
        this.elements = elements;
        calculateOffsets();
    }

    private void calculateOffsets() {
        var offset = 0;
        this.stride = 0;

        for(var element : elements) {
            element.setOffset(offset);
            offset += element.getSize();
            this.stride += element.getSize();
        }
    }

}
