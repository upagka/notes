package vip.daur.notes.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class HardcodedNotesRepository implements NotesRepository {

    @Override
    public List<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        HashSet<Integer> tags = new HashSet<>();
        tags.add(1);
        tags.add(3);

        notes.add(new Note(1, "Купи хлеб", "свежий бородинский", tags, 2, "25/01/2021 12.000"));
        notes.add(new Note(1, "Посмотри фильм", "Прибытие", tags, 3, "20/01/2021 12.000"));
        notes.add(new Note(1, "Позвони дяде", "", tags, 2, "25/01/2021 12.000"));
        notes.add(new Note(1, "прочитай про корутины", "внимательно", tags, 5, "25/01/2021 12.000"));
        notes.add(new Note(1, "Сделай домашнюю работу", "Фрагменты ДЗ№6", tags, 4, "25/01/2021 12.000"));

        return notes;
    }

    @Override
    public void addNote(Note note) {

    }

    @Override
    public void removeNote(Note note) {

    }
}
