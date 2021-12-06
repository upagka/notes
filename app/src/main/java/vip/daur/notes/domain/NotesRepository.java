package vip.daur.notes.domain;

import java.util.List;

public interface NotesRepository {

    List<Note> getAllNotes();

    void addNote(Note note);

    void removeNote(Note note);

}
