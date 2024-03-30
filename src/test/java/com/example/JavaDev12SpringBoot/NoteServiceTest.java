package com.example.JavaDev12SpringBoot;

import com.example.JavaDev12SpringBoot.model.Note;
import com.example.JavaDev12SpringBoot.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class NoteServiceTest {
    private NoteService noteService;

    @BeforeEach
    void initNoteService(){
        noteService = new NoteService();
    }

    @Test
    void testAddNote(){
        Note note = new Note();
        note.setTitle("Test Title for Note");
        note.setContent("Test Content for Note");

        Note addedNote = noteService.addNote(note);

        assertNotNull(addedNote.getId());
        assertEquals("Test Title for Note", addedNote.getTitle());
        assertEquals("Test Content for Note", addedNote.getContent());

    }

    @Test
    void testUpdateNote(){
        Note note = new Note();
        note.setTitle("Note");
        note.setContent("First Note");

        Note addedNote = noteService.addNote(note);
        UUID noteId = addedNote.getId();

        Note updatedNote = new Note();
        updatedNote.setId(noteId);
        updatedNote.setTitle("New Title");
        updatedNote.setContent("New Content");

        noteService.update(updatedNote);

        Optional<Note> checkNote = noteService.getNoteById(noteId);

        assertEquals("New Title",checkNote.get().getTitle());
        assertEquals("New Content", checkNote.get().getContent());
    }

    @Test
    void testDeleteNote(){

        Note note = new Note(UUID.randomUUID(), "Title", "Content");

        noteService.addNote(note);
        noteService.deleteById(note.getId());

        assertTrue(noteService.listAll().isEmpty());
    }
    @Test
    void testListAllNotes(){
        Note note1 = new Note();
        note1.setTitle("Note1");
        note1.setContent("First Note");

        Note note2 = new Note();
        note2.setTitle("Note2");
        note2.setContent("Second Note");

        noteService.addNote(note1);
        noteService.addNote(note2);

        assertEquals(2,noteService.listAll().size());
    }
}
