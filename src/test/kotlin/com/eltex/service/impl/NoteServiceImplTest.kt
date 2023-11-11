package com.eltex.service.impl

import com.eltex.model.Note
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals

class NoteServiceImplTest {

    @Test
    fun saveNonExist() {
        val noteService = NoteServiceImpl()
        val time = Instant.now()

        noteService.save(Note(0, "Text", time))

        val list1 = noteService.getAll()
        val list2 = listOf(Note(1L, "Text", time))

        assertEquals(list1, list2)
    }

    @Test
    fun saveAlreadyExist() {
        val noteService = NoteServiceImpl()
        val time = Instant.now()

        noteService.save(Note(0, "Text", time))

        Thread.sleep(1000)

        noteService.save(Note(1L, "UpdatedText", time))

        val updatedNote = noteService.getAll()[0]

        assertEquals(updatedNote.id, 1L)
        assertEquals(updatedNote.text, "UpdatedText")
        assertEquals(updatedNote.createdAt, time)
        assertNotEquals(updatedNote.updatedAt, time)
    }

    @Test
    fun saveNonValidId() {
        val noteService = NoteServiceImpl()

        assertFailsWith<IllegalArgumentException> {
            noteService.save(Note(1, "Text", Instant.now()))
        }
    }

    @Test
    fun getAll() {
        val noteService = NoteServiceImpl()
        val time = Instant.now()

        noteService.save(Note(0, "Text1", time))
        noteService.save(Note(0, "Text2", time))
        noteService.save(Note(0, "Text3", time))

        val list1 = noteService.getAll()
        val list2 = listOf(
            Note(1, "Text1", time),
            Note(2, "Text2", time),
            Note(3, "Text3", time),
        )

        assertEquals(list1, list2)
    }

    @Test
    fun getAllUniqueTexts() {
        val noteService = NoteServiceImpl()
        val time = Instant.now()

        noteService.save(Note(0, "Text1", time))
        noteService.save(Note(0, "Text1", time))
        noteService.save(Note(0, "Text2", time))
        noteService.save(Note(0, "Text2", time))

        val list1 = noteService.getAllUniqueTexts()
        val list2 = listOf(
            "Text1",
            "Text2",
        )

        assertEquals(list1, list2)
    }

    @Test
    fun getBefore() {
        val noteService = NoteServiceImpl()
        val time = Instant.now()

        noteService.save(Note(0, "Text1", time))
        noteService.save(Note(0, "Text2", time))
        noteService.save(Note(0, "Text3", time))
        noteService.save(Note(0, "Text4", time))
        noteService.save(Note(0, "Text5", time))

        val list1 = noteService.getBefore(2, 3)
        val list2 = listOf(
            Note(1, "Text1", time),
            Note(2, "Text2", time),
        )

        assertEquals(list1, list2)
    }

    @Test
    fun getAfter() {
        val noteService = NoteServiceImpl()
        val time = Instant.now()

        noteService.save(Note(0, "Text1", time))
        noteService.save(Note(0, "Text2", time))
        noteService.save(Note(0, "Text3", time))
        noteService.save(Note(0, "Text4", time))
        noteService.save(Note(0, "Text5", time))

        val list1 = noteService.getAfter(2, 3)
        val list2 = listOf(
            Note(4, "Text4", time),
            Note(5, "Text5", time),
        )

        assertEquals(list1, list2)
    }

}