package com.wincovid.enums;

public enum Fingerprint {
    Present("Present"),
    Absent("Absent"),
    ;


    private String finger;

    private Fingerprint(String finger) {
        this.finger = finger;
    }

    public String getFinger() {
        return finger;
    }
}
