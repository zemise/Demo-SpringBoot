package io.github.zemise.springbootevent;


import io.github.zemise.springbootevent.event.UserLoggedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ReleaseEvent {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public ReleaseEvent(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishUserLogged(){
        UserLoggedEvent userLoggedEvent = new UserLoggedEvent(this);
        eventPublisher.publishEvent(userLoggedEvent);
    }
}
