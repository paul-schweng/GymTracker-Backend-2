package de.dhbw.cleanproject.domain.bodydata;

public enum BodyPart {
    WEIGHT(false),
    BREAST(false),
    HIP(false),
    WAIST(false),
    SHOULDERS(false),
    BICEP(true),
    FOREARM(true),
    LEG(true);

    private final boolean dual;

    BodyPart(boolean dual) {
        this.dual = dual;
    }

    public boolean isDual() {
        return this.dual;
    }
}
