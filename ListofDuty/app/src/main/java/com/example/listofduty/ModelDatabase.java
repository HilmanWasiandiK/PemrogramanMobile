package com.example.listofduty;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Model.class}, version = 1)
public abstract class ModelDatabase extends RoomDatabase {
    private static ModelDatabase modelDatabase;

    public abstract ModelDAO modelDAO();

    public static synchronized ModelDatabase getInstance(Context context) {
        if(modelDatabase == null) {
            modelDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    ModelDatabase.class, "m0del_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return modelDatabase;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(modelDatabase).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void> {
        private ModelDAO modelDAO;

        private PopulateDBAsyncTask(ModelDatabase db) {
            modelDAO = db.modelDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
