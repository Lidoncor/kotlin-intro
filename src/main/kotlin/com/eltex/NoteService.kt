package com.eltex

import java.time.Instant

class NoteService {

    private var note = Note("Записка", Instant.now())

    fun updateText(text: String) {
        note = note.copy(text = text, updatedAt = Instant.now())
    }

    fun getNote(): Note = note

}