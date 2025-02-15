package milori.junis.weather.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun String.toLocalDateTime(pattern: String): LocalDateTime {
    val indexOfDot = this.lastIndexOf('.')
    val stringWithoutMillis = if (indexOfDot != -1) {
        this.substring(0, indexOfDot)
    } else {
        this
    }

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
    return LocalDateTime.parse(stringWithoutMillis, formatter)
}

fun String.toLocalTime(pattern: String): LocalTime {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
    return LocalTime.parse(this, formatter)
}

fun String.toLocalDate(pattern: String): LocalDate {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
    return LocalDate.parse(this, formatter)
}

fun LocalDateTime.toStringDateTime(pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return this.format(formatter)
}

fun LocalDate.toStringDate(pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return this.format(formatter)
}