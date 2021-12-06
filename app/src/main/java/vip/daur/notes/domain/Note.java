package vip.daur.notes.domain;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Set;

public class Note implements Parcelable {

    private int id;

    private String title;

    private String text;

    private Set<Integer> tags;

    private int priority;

    private String date;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Set<Integer> getTags() {
        return tags;
    }

    public int getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }


    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        text = in.readString();
        priority = in.readInt();
        date = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(text);
        dest.writeInt(priority);
        dest.writeString(date);
    }
}
