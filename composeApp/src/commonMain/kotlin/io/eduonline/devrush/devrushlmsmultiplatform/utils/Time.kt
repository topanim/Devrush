package io.eduonline.devrush.devrushlmsmultiplatform.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun parseStringToLocalDateTime(time: String): LocalDateTime =
    Instant.parse(time).toLocalDateTime(TimeZone.currentSystemDefault())

fun parseStringToMillis(time: String): Long =
    Instant.parse(time).toEpochMilliseconds()


fun simpleFormatTime(time: Long): String {
    val localDateTime = Instant
        .fromEpochMilliseconds(time)
        .toLocalDateTime(TimeZone.currentSystemDefault())

    val year = localDateTime.year.toString().padStart(4, '0')
    val month = localDateTime.monthNumber.toString().padStart(2, '0')
    val day = localDateTime.dayOfMonth.toString().padStart(2, '0')

    return "${day}.${month}.${year}"
}

fun formatTime(time: Long): String {
    val localDateTime = Instant
        .fromEpochMilliseconds(time)
        .toLocalDateTime(TimeZone.currentSystemDefault())

    val year = localDateTime.year.toString().padStart(4, '0')
    val month = localDateTime.monthNumber.toString().padStart(2, '0')
    val day = localDateTime.dayOfMonth.toString().padStart(2, '0')
    val hour = localDateTime.hour.toString().padStart(2, '0')
    val minute = localDateTime.minute.toString().padStart(2, '0')
    val second = localDateTime.second.toString().padStart(2, '0')

    return "$year-$month-${day}T$hour:$minute:${second}.000Z"
}