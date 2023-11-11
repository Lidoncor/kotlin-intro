package com.eltex

fun main(args: Array<String>) {

    var noteService = NoteService()

    var note = noteService.getNote()

    println(note)

    Thread.sleep(60000)

    noteService.updateText("Новая записка")

    note = noteService.getNote()

    println(note)

}