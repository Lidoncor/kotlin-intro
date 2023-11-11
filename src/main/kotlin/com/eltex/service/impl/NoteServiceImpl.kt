package com.eltex.service.impl

import com.eltex.model.Note
import com.eltex.service.NoteService
import java.time.Instant

class NoteServiceImpl : NoteService {

    private var noteId = 1L

    private val notes: MutableList<Note> = ArrayList()

    override fun save(note: Note) {
        if (note.id == 0L) {
            notes.add(note.copy(id = noteId++))
        } else {
            val noteIndex = notes.indexOfFirst { it.id == note.id }

            if (noteIndex < 0) throw IllegalArgumentException("Указан некорректный id")

            notes[noteIndex] = note.copy(updatedAt = Instant.now())
        }
    }

    override fun getAll(): List<Note> = notes
        .toList()

    override fun getAllUniqueTexts(): List<String> = notes
        .asSequence()
        .map { it.text }
        .distinct()
        .toList()

    override fun getBefore(count: Int, id: Long): List<Note> = notes
        .takeWhile { it.id != id }
        .take(count)

    override fun getAfter(count: Int, id: Long): List<Note> = notes
        .takeLastWhile { it.id != id }
        .take(count)

}