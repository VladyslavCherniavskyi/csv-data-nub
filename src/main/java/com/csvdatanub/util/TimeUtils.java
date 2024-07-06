package com.csvdatanub.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TimeUtils {

    private static final String FORMAT = "dd-MM-YYYY HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    public static String formatter(LocalDateTime dateTime) {
        return dateTime.atOffset(ZoneOffset.UTC).format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime now() {
        return Stream.of(LocalDateTime.now(ZoneOffset.UTC))
                .peek(dateTime -> dateTime.format(DATE_TIME_FORMATTER))
                .findFirst()
                .get();
    }

}