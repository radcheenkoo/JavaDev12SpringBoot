package com.example.JavaDev12SpringBoot.service;

import com.example.JavaDev12SpringBoot.Note;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
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
        Optional<Note> note = notes.stream()
                .filter(note1 -> note1.getId().equals(id)).findFirst();

        if (note.isPresent()){
            notes.remove(note.get());
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void update(Note note){
        for (Note n: notes) {
            if (n.getId().equals(note.getId())){
                n.setTitle(note.getTitle());
                n.setContent(note.getContent());
                return;
            }
        }
        throw new NoSuchElementException("Нотатки не знайдено");
    }
    public Optional<Note> getNoteById(UUID id){
        return notes.stream().filter(note -> note.getId().equals(id)).findFirst();
    }
}
