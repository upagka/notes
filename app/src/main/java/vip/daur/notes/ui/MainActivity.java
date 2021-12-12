package vip.daur.notes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import vip.daur.notes.R;
import vip.daur.notes.domain.Note;
import vip.daur.notes.ui.detail.NoteDetailsFragment;
import vip.daur.notes.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity {

    public static final String ARG_NOTE = "ARG_NOTE";

    private Note selectedNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null && savedInstanceState.containsKey("key")) {
            Toast.makeText(this, "YOOOOOO", Toast.LENGTH_SHORT).show();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes_container, new NotesListFragment())
                .replace(R.id.details_container, new NoteDetailsFragment())
                .commit();

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_NOTE)) {
            selectedNote = savedInstanceState.getParcelable(ARG_NOTE);

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                showDetails();
            }
        }

        getSupportFragmentManager()
                .setFragmentResultListener(NotesListFragment.RESULT_KEY, this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        selectedNote = result.getParcelable(NotesListFragment.ARG_NOTE);

                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            showDetails();
                        } else {

                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.notes_container, new NoteDetailsFragment().newInstance(selectedNote))
                                    .addToBackStack("details")
                                    .commit();

                        }
                    }
                });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (selectedNote != null) {
            outState.putParcelable(ARG_NOTE, selectedNote);
        }
    }

    private void showDetails() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(NoteDetailsFragment.ARG_NOTE, selectedNote);
        getSupportFragmentManager()
                .setFragmentResult(NoteDetailsFragment.KEY_RESULT, bundle);
    }

}