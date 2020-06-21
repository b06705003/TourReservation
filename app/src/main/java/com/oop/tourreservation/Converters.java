package com.oop.tourreservation;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters { // used to convert date to long and vice versa
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
