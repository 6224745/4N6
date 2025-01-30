package org.ballestero.model

import java.time.LocalDateTime

data class Secret(
    val nom: String,
    val date: LocalDateTime,
    val nbGrand: Long
)