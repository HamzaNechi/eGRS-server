package com.orange.orangegrs.utils.events;

import com.orange.orangegrs.entities.Visite;
import org.springframework.context.ApplicationEvent;

public class NewVisiteAddedEvent extends ApplicationEvent {
    public NewVisiteAddedEvent(Object source) {
        super(source);
    }
}
