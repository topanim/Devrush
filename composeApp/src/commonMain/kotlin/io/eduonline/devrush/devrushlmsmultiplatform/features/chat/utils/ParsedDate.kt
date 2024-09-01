package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun getParsedDate(date: String): LocalDateTime {
    return Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault())
}