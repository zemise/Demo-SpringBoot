package io.github.zemise.springbootevent.listener;

import io.github.zemise.springbootevent.event.UserLoggedEvent;
import org.springframework.stereotype.Component;

@Component
public class EventListener {
    @org.springframework.context.event.EventListener
    public void handleUserLoggedEvent(UserLoggedEvent event){
        System.out.println("用户登录了");
    }
}
