package org.thebungine.engine.render.shader;

import org.thebungine.engine.core.BungineContext;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ShaderLibrary {

    private ShaderLibrary() {

    }

    private static final Map<String, Shader> library = new HashMap<>();

    public static Shader add(String name, Shader shader) {
        library.put(name, shader);
        return shader;
    }

    public static Shader add(String name, String vertexShader, String fragmentShader) {
        var shader = BungineContext.getInstance().getRendererFactory().createShader(vertexShader, fragmentShader);
        return add(name, shader);
    }

    public static Shader load(String name, URL path) throws IOException {
        var shader = BungineContext.getInstance().getRendererFactory().createShader(path);
        return add(name, shader);
    }

    public static Shader load(URL path) throws IOException {
        var shader = BungineContext.getInstance().getRendererFactory().createShader(path);
        var filename = path.getFile();
        var dotLocation = filename.lastIndexOf(".");
        var slashLocation = filename.lastIndexOf("/");

        return add(path.getFile().substring(slashLocation + 1, dotLocation), shader);
    }

    public static Shader get(String name) {
        return library.get(name);
    }
}
