package org.thebungine.engine.event;

public interface EventListener<T> {

    void onEvent(T event);
}