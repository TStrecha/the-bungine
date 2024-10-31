package eu.tstrecha.bungine.core.event;

public interface EventListener<T> {

    void onEvent(T event);
}