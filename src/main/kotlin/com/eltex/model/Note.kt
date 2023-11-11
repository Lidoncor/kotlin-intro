package com.eltex.model

import java.time.Instant

data class Note(
    val id: Long,
    val text: String,
    val createdAt: Instant,
    val updatedAt: Instant = createdAt,
)
