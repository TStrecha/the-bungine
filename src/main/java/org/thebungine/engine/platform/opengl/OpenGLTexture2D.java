package org.thebungine.engine.platform.opengl;

import lombok.Getter;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL45;
import org.thebungine.engine.render.texture.Texture2D;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

@Getter
public class OpenGLTexture2D implements Texture2D {

    private final int textureId;

    private final String path;
    private final int width;
    private final int height;
    private final int channels;


    public OpenGLTexture2D(String path) throws IOException {
        this.path = path;
        var image = ImageIO.read(new File(path));

        this.width = image.getWidth();
        this.height = image.getHeight();
        this.channels = image.getColorModel().getPixelSize() / 8;

        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

        var buffer = ByteBuffer.allocateDirect(width * height * channels);
        for (int y = height - 1; y >= 0; y--) {
            for (var x = 0; x < width; x++) {
                var pixel = pixels[y * width + x];

                buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red
                buffer.put((byte) ((pixel >> 8) & 0xFF));  // Green
                buffer.put((byte) (pixel & 0xFF));         // Blue
                buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha
            }
        }
        buffer.flip();

        this.textureId = GL45.glCreateTextures(GL11.GL_TEXTURE_2D);
        if(channels == 3) {
            GL45.glTextureStorage2D(textureId, 1, GL11.GL_RGB8, width, height);
        } else {
            GL45.glTextureStorage2D(textureId, 1, GL11.GL_RGBA8, width, height);
        }

        GL45.glTextureParameteri(textureId, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL45.glTextureParameteri(textureId, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        if(channels == 3) {
            GL45.glTextureSubImage2D(textureId, 0, 0, 0, width, height, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, buffer);
        } else {
            GL45.glTextureSubImage2D(textureId, 0, 0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
        }
    }


    @Override
    public void bind(int slot) {
        GL45.glBindTextureUnit(slot, textureId);
    }
}
