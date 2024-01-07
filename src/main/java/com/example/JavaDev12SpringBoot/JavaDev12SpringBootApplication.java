package com.example.JavaDev12SpringBoot;

import com.example.JavaDev12SpringBoot.service.NoteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaDev12SpringBootApplication {

	public static void main(String[] args) {

		NoteService noteService = new NoteService();

		Note note = new Note();
		note.setTitle("First");
		note.setContent("Something1");
		Note note1 = new Note();
		note1.setTitle("second");
		note1.setContent("Something2");
		Note note2 = new Note();
		note2.setTitle("third");
		note2.setContent("Something3");
		Note note3 = new Note();
		note3.setTitle("Fourth");
		note3.setContent("Something4");

		noteService.addNote(note);
		noteService.addNote(note1);
		noteService.addNote(note2);
		noteService.addNote(note3);

		noteService.listAll().forEach(n -> {
			System.out.println(n.getId() + ", " + n.getTitle() + ", " + n.getContent());
		});

		System.out.println(noteService.getNoteById(note2.getId()).getTitle());
		noteService.deleteById(note2.getId());
		System.out.println(noteService.getNoteById(note3.getId()).getTitle());

		Note note4 = noteService.getNoteById(note.getId());

		note4.setTitle("new title");
		note4.setContent("new content");


		noteService.listAll().forEach(n -> {
			System.out.println(n.getId() + ", " + n.getTitle() + ", " + n.getContent());
		});



	}

}
