package com.example.fitpals;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class WorkoutRepository {
    private WorkoutDao workoutDao;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutRepository(Application application) {
        WorkoutDatabase db = WorkoutDatabase.getDatabase(application);
        workoutDao = db.workoutDao();
        allWorkouts = workoutDao.getAllWorkouts();
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    public void insert(Workout workout) {
        WorkoutDatabase.databaseWriteExecutor.execute(() -> {
            workoutDao.insert(workout);
        });
    }

    public void delete(Workout workout) {
        WorkoutDatabase.databaseWriteExecutor.execute(() -> {
            workoutDao.delete(workout);
        });
    }

    public void update(Workout workout) {
        WorkoutDatabase.databaseWriteExecutor.execute(() -> {
            workoutDao.update(workout);
        });
    }

    public void deleteAll() {
        WorkoutDatabase.databaseWriteExecutor.execute(() -> {
            workoutDao.deleteAll();
        });
    }
}
