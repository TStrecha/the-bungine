package org.thebungine.engine.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventDispatcher {

    private final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listeners = new HashMap<>();
    private final List<EventListener<? super Event>> generalListeners = new LinkedList<>();

    private EventDispatcher() {

    }

    public <T extends Event> void registerListener(Class<T> eventType, EventListener<T> listener) {
        listeners
                .computeIfAbsent(eventType, i -> new ArrayList<>())
                .add(listener);
    }

    public void registerGeneralListener(EventListener<? super Event> listener) {
        generalListeners.add(listener);
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> void dispatchEvent(T event) {
        List<EventListener<? extends Event>> listeners = this.listeners.get(event.getClass());
        if (listeners != null) {
            for (EventListener<? extends Event> listener : listeners) {
                ((EventListener<T>) listener).onEvent(event);
            }
        }

        generalListeners.forEach(listener -> listener.onEvent(event));
    }

    private static EventDispatcher instance;

    public static EventDispatcher getInstance() {
        if(instance == null) {
            instance = new EventDispatcher();
        }

        return instance;
    }
}
