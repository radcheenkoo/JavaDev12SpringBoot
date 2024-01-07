package com.example.JavaDev12SpringBoot.service;

import com.example.JavaDev12SpringBoot.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class NoteService {

    private List<Note> notes = new ArrayList<>();

    public List<Note> listAll(){
        return notes;
    }

    public Note addNote(Note note){
        note.setId(UUID.randomUUID());
        notes.add(note);

        return note;
    }

    public void deleteById(UUID id){
        for (Note n : notes) {
            if (n.getId().equals(id)){
                notes.remove(n);
//                log.info("Note removed");
            }
        }
    }

    public void update(Note note){
        for (Note n: notes) {
            if (n.getId().equals(note.getId())){
                n.setTitle(note.getTitle());
                n.setContent(note.getContent());
//                log.info("Note update");
            }else{
                throw new NullPointerException("Нотатки не знайдено");
            }
        }
    }
    public Note getNoteById(UUID id){
        for (Note n : notes) {
            if (n.getId().equals(id)){
//                log.info("Note found");
                return n;
            }
        }
//        log.info("Note Do not found");
        return null;
    }
}
