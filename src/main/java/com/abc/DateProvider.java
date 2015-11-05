package com.abc;

import java.util.Calendar;
import java.util.Date;

public enum DateProvider {
    instance;

    public static DateProvider getInstance() {
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
