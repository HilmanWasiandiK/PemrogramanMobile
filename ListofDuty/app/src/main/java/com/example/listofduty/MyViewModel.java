package com.example.listofduty;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private ModelRepository modelRepository;
    private LiveData<List<Model>> allModels;

    public MyViewModel(@NonNull Application application) {
        super(application);
        modelRepository = new ModelRepository(application);
        allModels = modelRepository.getAllTask();
    }

    public void addTask(Model model) {
        modelRepository.addTask(model);
    }

    public void updateTask(Model model) {
        modelRepository.updateTask(model);
    }

    public void deleteTask(Model model) {
        modelRepository.deleteTask(model);
    }

    public void deleteAllTask() {
        modelRepository.deleteAllTask();
    }

    public void deleteDoneTask() {
        modelRepository.deleteDoneTask();
    }

    public LiveData<List<Model>> getAllTask() {
        return allModels;
    }
}
