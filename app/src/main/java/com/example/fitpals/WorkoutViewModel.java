package com.example.fitpals;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {
    private WorkoutRepository repository;
    private final LiveData<List<Workout>> allWorkouts;

    public WorkoutViewModel(Application application) {
        super(application);
        repository = new WorkoutRepository(application);
        allWorkouts = repository.getAllWorkouts();
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    public void insert(Workout workout) {
        repository.insert(workout);
    }

    public void delete(Workout workout) {
        repository.delete(workout);
    }

    public void update(Workout workout) {
        repository.update(workout);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
