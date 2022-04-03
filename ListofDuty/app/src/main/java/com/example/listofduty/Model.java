package com.example.listofduty;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks_entity")
public class Model implements Parcelable {
    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "task_title")
    @NonNull
    final private String title;

    @ColumnInfo(name = "task_description")
    final private String description;

    @ColumnInfo(name = "task_deadline")
    final private String deadline;

    @ColumnInfo(name = "task_checkbox")
    @NonNull
    private boolean checkbox;

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

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setCheckbox(boolean checkbox){
        this.checkbox = checkbox;
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
