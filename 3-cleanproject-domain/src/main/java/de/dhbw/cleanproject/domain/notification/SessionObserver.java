package de.dhbw.cleanproject.domain.notification;

import javax.websocket.Session;
import java.io.IOException;

public class SessionObserver implements Observer {
    private final Session session;

    public SessionObserver(Session session) {
        this.session = session;
    }

    public void update(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
