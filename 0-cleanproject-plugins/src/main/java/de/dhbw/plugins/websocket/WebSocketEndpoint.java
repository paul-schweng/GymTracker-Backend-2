package de.dhbw.plugins.websocket;

import de.dhbw.cleanproject.application.notification.NotificationService;
import de.dhbw.cleanproject.domain.notification.SessionObserver;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint(value = "/websocket/")
public class WebSocketEndpoint {
    private SessionObserver sessionObserver;
    private final NotificationService notificationService;

    public WebSocketEndpoint(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.sessionObserver = new SessionObserver(session);
        notificationService.registerObserver(sessionObserver);
    }

    @OnClose
    public void onClose(Session session) {
        notificationService.removeObserver(sessionObserver);
    }
}
