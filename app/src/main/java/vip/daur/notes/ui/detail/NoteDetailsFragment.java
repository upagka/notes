package vip.daur.notes.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import vip.daur.notes.R;
import vip.daur.notes.domain.Note;
import vip.daur.notes.ui.list.NotesListFragment;

public class NoteDetailsFragment extends Fragment {
    public static final String ARG_NOTE = "ARG_NOTE";
    public static final String KEY_RESULT = "NoteDetailsFragment_KEY_RESULT";
    private TextView noteTitle;
    private TextView noteText;

    public NoteDetailsFragment() {
        super(R.layout.fragment_note_details);
    }

    public static NoteDetailsFragment newInstance(Note note) {
        NoteDetailsFragment fragment = new NoteDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteTitle = view.findViewById(R.id.note_title);

        noteText = view.findViewById(R.id.note_text);

        if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {
            displayDetails(getArguments().getParcelable(ARG_NOTE));
        }

        getParentFragmentManager()
                .setFragmentResultListener(KEY_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = result.getParcelable(NotesListFragment.ARG_NOTE);
                        displayDetails(note);
                    }
                });
    }

    private void displayDetails(Note note) {
        noteTitle.setText(note.getTitle());
        noteText.setText(note.getText());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
