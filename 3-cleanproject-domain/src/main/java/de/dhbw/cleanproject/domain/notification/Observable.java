package de.dhbw.cleanproject.domain.notification;


public interface Observable {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);

}
