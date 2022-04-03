package com.example.listofduty;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ModelDAO {

    @Insert
    void addTask(Model task);

    @Update
    void updateTask(Model task);

    @Delete
    void deleteTask(Model task);

    @Query("SELECT * FROM tasks_entity ORDER BY task_deadline ASC")
    LiveData<List<Model>> getAllTask();

    @Query("DELETE FROM tasks_entity")
    void deleteAllTask();

    @Query("DELETE FROM tasks_entity WHERE task_checkbox IS 1")
    void deleteDoneTask();
}
