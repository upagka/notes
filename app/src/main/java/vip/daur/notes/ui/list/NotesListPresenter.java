package vip.daur.notes.ui.list;

import java.util.List;

import vip.daur.notes.domain.Note;
import vip.daur.notes.domain.NotesRepository;

public class NotesListPresenter {


    private NotesListView view;

    private NotesRepository repository;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void refresh() {

        List<Note> result = repository.getAllNotes();

        view.showNotes(result);
    }
}
