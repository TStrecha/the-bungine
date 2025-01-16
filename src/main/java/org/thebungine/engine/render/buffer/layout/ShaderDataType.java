package org.thebungine.engine.render.buffer.layout;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShaderDataType {
    FLOAT(4, 1),
    FLOAT_2(4 * 2, 2),
    FLOAT_3(4 * 3, 3),
    FLOAT_4(4 * 4, 4),
    MAT_3(4 * 3 * 3, 9),
    MAT_4(4 * 4 * 4, 16),
    INT(4, 1),
    INT_2(4 * 2, 2),
    INT_3(4 * 3, 3),
    INT_4(4 * 4, 4),
    BOOL(1, 1);

    private final int size;
    private final int elementCount;
}
