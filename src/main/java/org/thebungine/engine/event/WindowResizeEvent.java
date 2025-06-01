package org.thebungine.engine.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WindowResizeEvent extends Event {

    private int width;
    private int height;
}
