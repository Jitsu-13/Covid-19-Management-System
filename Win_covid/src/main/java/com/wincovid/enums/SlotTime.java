package com.wincovid.enums;

public enum SlotTime {
    SLOT1("09:00am-10:00am"),
    SLOT2("10:00am-11:00am"),
    SLOT3("11:00am-12:00pm"),
    SLOT4("12:00am-01:00pm"),
    SLOT5("01:00pm-02:00pm"),
    SLOT6("02:00pm-03:00pm"),
    SLOT7("03:00pm-04:00pm"),
    SLOT8("04:00pm-05:00pm"),
    SLOT9("05:00pm-06:00pm");

    private String timeslot;

    private SlotTime(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getTimeslot() {
        return timeslot;
    }
}
