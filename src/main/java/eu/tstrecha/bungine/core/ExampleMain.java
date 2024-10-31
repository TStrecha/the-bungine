package eu.tstrecha.bungine.core;

import eu.tstrecha.bungine.core.window.WindowProperties;
import eu.tstrecha.bungine.core.window.WindowsWindow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleMain {

    public static void main(String[] args) {
        log.info("Log initialized.");

        var app = new Application(){};
        app.setWindow(new WindowsWindow(WindowProperties.builder().build()));
        app.run();
    }
}