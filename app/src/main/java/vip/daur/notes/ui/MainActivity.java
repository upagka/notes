package vip.daur.notes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import vip.daur.notes.R;
import vip.daur.notes.domain.Note;

public class MainActivity extends AppCompatActivity {

    public static final String ARG_NOTE = "ARG_NOTE";

    private Note selectedNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null && savedInstanceState.containsKey(ARG_NOTE)) {
            selectedNote = savedInstanceState.getParcelable(ARG_NOTE);

        }
    }

}