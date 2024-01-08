package com.example.JavaDev12SpringBoot.service;

import com.example.JavaDev12SpringBoot.Note;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestService {
    private static final Logger log = LoggerFactory.getLogger(TestService.class);
    private NoteService noteService;

    @Autowired
    public void setNoteService(NoteService noteService){
        this.noteService = noteService;
    }

    @PostConstruct
    public void init(){

        testDeleteNote();

        testAddNote();

        testUpdateNote();


    }

    private void testAddNote(){
        Note note = new Note();
        note.setTitle("Test Title for Note");
        note.setContent("Test Content for Note");

        Note addedNote = noteService.addNote(note);

        log.info("Id added note ---> " + addedNote.getId());
        log.info("Title added note ---> " + addedNote.getTitle());
        log.info("Content added note ---> " + addedNote.getContent());
    }

    private void testUpdateNote(){
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

        Note checkNote = noteService.getNoteById(noteId);

        log.info("testUpdateNote ==>->>> ");
        log.info(String.valueOf(("New Title" == checkNote.getTitle())));
        log.info(String.valueOf(("New Content" == checkNote.getContent())));

    }

    private void testDeleteNote(){

        Note note = new Note(UUID.randomUUID(), "Title", "Content");

        noteService.addNote(note);
        noteService.deleteById(note.getId());

        log.info("testDeleteNote --->>> " + noteService.listAll().isEmpty());
    }

    void testListAllNotes(){
        Note note1 = new Note();
        note1.setTitle("Note1");
        note1.setContent("First Note");

        Note note2 = new Note();
        note2.setTitle("Note2");
        note2.setContent("Second Note");

        noteService.addNote(note1);
        noteService.addNote(note2);

        log.info("testListAllNotes --->>> " + (2 == noteService.listAll().size()));

    }


}
