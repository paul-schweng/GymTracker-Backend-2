package de.dhbw.cleanproject.domain.bodydata;

public enum BodyPartType {
    BICEP(true),
    FOREARM(true),
    LEG(true),
    BREAST(false),
    HIP(false),
    SHOULDERS(false),
    WAIST(false),
    WEIGHT(false);

    private final boolean isDual;

    BodyPartType(boolean isDual) {
        this.isDual = isDual;
    }

    public boolean isDual() {
        return isDual;
    }
}
