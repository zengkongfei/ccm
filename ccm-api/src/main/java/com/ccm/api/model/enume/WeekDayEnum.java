package com.ccm.api.model.enume;

import java.util.Calendar;

public enum WeekDayEnum {
    MONDAY(Calendar.MONDAY-1), TUESDAY(Calendar.TUESDAY-1), WEDNESDAY(
            Calendar.WEDNESDAY-1), THURSDAY(Calendar.THURSDAY-1), FRIDAY(
            Calendar.FRIDAY-1), SATURDAY(Calendar.SATURDAY-1), SUNDAY(
            Calendar.SUNDAY-1);

    WeekDayEnum(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
