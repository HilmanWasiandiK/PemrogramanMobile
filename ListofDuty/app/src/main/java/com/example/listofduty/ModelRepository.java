package com.example.listofduty;

import android.app.Application;
import android.os.AsyncTask;
import android.view.Display;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ModelRepository {
    private ModelDAO modelDAO;
    private LiveData<List<Model>> allModels;

    public ModelRepository(Application application) {
         ModelDatabase modelDatabase = ModelDatabase.getInstance(application);
         modelDAO = modelDatabase.modelDAO();
         allModels = modelDAO.getAllTask();
    }

    public void addTask(Model model) {
        new InsertModelAsyncTask(modelDAO).execute(model);
    }

    public void updateTask(Model model) {
        new UpdateModelAsyncTask(modelDAO).execute(model);
    }

    public void deleteTask(Model model) {
        new DeleteModelAsyncTask(modelDAO).execute(model);
    }

    public void deleteAllTask() {
        new DeleteAllTaskModelAsyncTask(modelDAO).execute();
    }

    public void deleteDoneTask() {
        new DeleteDoneTaskModelAsyncTask(modelDAO).execute();
    }

    public LiveData<List<Model>> getAllTask() {
        return allModels;
    }

    private static class InsertModelAsyncTask extends AsyncTask<Model,Void,Void> {
        private ModelDAO modelDAO;

        private InsertModelAsyncTask(ModelDAO modelDAO) {
            this.modelDAO = modelDAO;
        }

        @Override
        protected Void doInBackground(Model... models) {
            modelDAO.addTask(models[0]);
            return null;
        }
    }

    private static class UpdateModelAsyncTask extends AsyncTask<Model,Void,Void> {
        private ModelDAO modelDAO;

        private UpdateModelAsyncTask(ModelDAO modelDAO) {
            this.modelDAO = modelDAO;
        }

        @Override
        protected Void doInBackground(Model... models) {
            modelDAO.updateTask(models[0]);
            return null;
        }
    }

    private static class DeleteModelAsyncTask extends AsyncTask<Model,Void,Void> {
        private ModelDAO modelDAO;

        private DeleteModelAsyncTask(ModelDAO modelDAO) {
            this.modelDAO = modelDAO;
        }

        @Override
        protected Void doInBackground(Model... models) {
            modelDAO.deleteTask(models[0]);
            return null;
        }
    }

    private static class DeleteAllTaskModelAsyncTask extends AsyncTask<Void,Void,Void> {
        private ModelDAO modelDAO;

        private DeleteAllTaskModelAsyncTask(ModelDAO modelDAO) {
            this.modelDAO = modelDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            modelDAO.deleteAllTask();
            return null;
        }
    }

    private static class DeleteDoneTaskModelAsyncTask extends AsyncTask<Void,Void,Void> {
        private ModelDAO modelDAO;

        private DeleteDoneTaskModelAsyncTask(ModelDAO modelDAO) {
            this.modelDAO = modelDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            modelDAO.deleteDoneTask();
            return null;
        }
    }
}

