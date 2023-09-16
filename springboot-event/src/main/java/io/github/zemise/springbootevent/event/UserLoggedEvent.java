package io.github.zemise.springbootevent.event;

import org.springframework.context.ApplicationEvent;

public class UserLoggedEvent extends ApplicationEvent {
    public UserLoggedEvent(Object source) {
        super(source);
    }
}
