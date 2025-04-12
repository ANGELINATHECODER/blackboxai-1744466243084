package com.example.fitpals;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.fitpals.databinding.ActivityMainBinding;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private WorkoutViewModel workoutViewModel;
    private WorkoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize RecyclerView
        adapter = new WorkoutAdapter();
        binding.workoutList.setAdapter(adapter);
        binding.workoutList.setLayoutManager(new LinearLayoutManager(this));

        // Initialize ViewModel
        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        workoutViewModel.getAllWorkouts().observe(this, workouts -> {
            adapter.setWorkouts(workouts);
        });

        // Set up Add Workout button click listener
        binding.addWorkoutButton.setOnClickListener(v -> addWorkout());
    }

    private void addWorkout() {
        String exerciseName = binding.exerciseNameInput.getText().toString();
        String setsStr = binding.setsInput.getText().toString();
        String repsStr = binding.repsInput.getText().toString();
        String weightStr = binding.weightInput.getText().toString();

        if (exerciseName.isEmpty() || setsStr.isEmpty() || repsStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int sets = Integer.parseInt(setsStr);
            int reps = Integer.parseInt(repsStr);
            float weight = Float.parseFloat(weightStr);

            Workout workout = new Workout();
            workout.exerciseName = exerciseName;
            workout.sets = sets;
            workout.reps = reps;
            workout.weight = weight;
            workout.date = new Date();

            workoutViewModel.insert(workout);
            clearInputs();
            Toast.makeText(this, "Workout added successfully!", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputs() {
        binding.exerciseNameInput.setText("");
        binding.setsInput.setText("");
        binding.repsInput.setText("");
        binding.weightInput.setText("");
    }
}
