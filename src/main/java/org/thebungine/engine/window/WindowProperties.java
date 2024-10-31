package org.thebungine.engine.window;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WindowProperties {

    @Builder.Default
    private String title = "New window";
    @Builder.Default
    private Integer width = 1920;
    @Builder.Default
    private Integer height = 1080;
}
