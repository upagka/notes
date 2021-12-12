package vip.daur.notes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vip.daur.notes.R;
import vip.daur.notes.domain.Note;
import vip.daur.notes.ui.detail.NoteDetailsFragment;
import vip.daur.notes.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity {

    public static final String ARG_NOTE = "ARG_NOTE";

    private Note selectedNote;



    //Клики по навигации не вызывают Toast-ы. Не поняла причину.
    //Также студия показывает, что метод OnNavigationItemSelectedListener - depricated.
    //Так и не нашла, что использовать вместо него.
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Toast.makeText(MainActivity.this, "Notes selected", Toast.LENGTH_SHORT).show();

                    //showFragment(NotesListFragment.TAG);
                    return true;
                case R.id.navigation_search:

                    Toast.makeText(MainActivity.this, "Search selected", Toast.LENGTH_SHORT).show();

                    //showFragment(PreferencesFragment.TAG);
                    return true;
                case R.id.navigation_settings:

                    Toast.makeText(MainActivity.this, "Settings selected", Toast.LENGTH_SHORT).show();

                    //showFragment(SettingsFragment.TAG);
                    return true;
            }
            return false;
        }
    };



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


    private void showFragment(@NonNull String tag) {
//        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
//        if (fragment == null) {
//            switch (tag) {
//                case WelcomeFragment.TAG: {
//                    fragment = new WelcomeFragment();
//                    break;
//                }
//                case PreferencesFragment.TAG: {
//                    fragment = new PreferencesFragment();
//                    break;
//                }
//                case SettingsFragment.TAG: {
//                    fragment = new SettingsFragment();
//                    break;
//                }
//                default: {
//                    fragment = new WelcomeFragment();
//                    break;
//                }
//            }
//        }
    }
}