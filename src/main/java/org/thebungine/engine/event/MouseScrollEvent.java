package org.thebungine.engine.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MouseScrollEvent extends Event {

    private float xOffset;
    private float yOffset;
}
