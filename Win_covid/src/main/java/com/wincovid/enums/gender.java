package com.wincovid.enums;

public enum gender {
    Male("Male"),
    Female("Female"),
    Other("Other");

    private String gen;

    private gender(String gen) {
        this.gen = gen;
    }

    public String getGen() {
        return gen;
    }
}
