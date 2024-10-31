package eu.tstrecha.bungine.core.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WindowCloseEvent extends Event {

    private Long windowId;
}
