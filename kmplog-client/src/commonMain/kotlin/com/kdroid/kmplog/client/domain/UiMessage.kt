package com.kdroid.kmplog.client.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

sealed interface UiMessage {
    val id: Long

    data class Success(
        val message: String,
        override val id: Long = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).nanosecond.toLong(),
    ) : UiMessage

    data class Error(
        val message: String,
        override val id: Long = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).nanosecond.toLong(),
        val error: Throwable? = null,
    ) : UiMessage
}