package de.dhbw.cleanproject.application.notification;

import de.dhbw.cleanproject.domain.notification.Observable;
import de.dhbw.cleanproject.domain.notification.Observer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService implements Observable {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void createNotification(String message) {
        // logic to create a notification
        notifyObservers(message);
    }

    @Scheduled(cron = "0 0 0 * * ?") // this cron expression represents every day at midnight
    public void sendDailyNotification() {
        createNotification("This is your daily notification!");
    }
}
