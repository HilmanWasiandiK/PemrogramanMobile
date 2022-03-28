package com.example.listofduty;

import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {
    final private String title, description, deadline;
    final private boolean checkbox;

    public Model(String title, String description, String deadline, boolean checkbox) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.checkbox = checkbox;
    }

    protected Model(Parcel in) {
        title = in.readString();
        description = in.readString();
        deadline = in.readString();
        checkbox = in.readByte() != 0;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return title;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(deadline);
        parcel.writeByte((byte) (checkbox ? 1 : 0));
    }
}
