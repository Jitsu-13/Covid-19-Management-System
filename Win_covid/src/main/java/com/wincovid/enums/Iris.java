package com.wincovid.enums;

public enum Iris {
    Present("Present"),
    Absent("Absent"),
    ;


    private String ire;

    private Iris(String ire) {
        this.ire = ire;
    }

    public String getIre() {
        return ire;
    }
}
