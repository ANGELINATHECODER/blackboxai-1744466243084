package com.example.fitpals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {
    private List<Workout> workouts = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_item, parent, false);
        return new WorkoutViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        Workout current = workouts.get(position);
        holder.exerciseNameView.setText(current.getExerciseName());
        holder.setsRepsView.setText(String.format(Locale.getDefault(), 
            "%d sets × %d reps", current.getSets(), current.getReps()));
        holder.weightView.setText(String.format(Locale.getDefault(), 
            "%.1f kg", current.getWeight()));
        holder.dateView.setText(dateFormat.format(current.getDate()));
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
        notifyDataSetChanged();
    }

    static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        private final TextView exerciseNameView;
        private final TextView setsRepsView;
        private final TextView weightView;
        private final TextView dateView;

        private WorkoutViewHolder(View itemView) {
            super(itemView);
            exerciseNameView = itemView.findViewById(R.id.text_exercise_name);
            setsRepsView = itemView.findViewById(R.id.text_sets_reps);
            weightView = itemView.findViewById(R.id.text_weight);
            dateView = itemView.findViewById(R.id.text_date);
        }
    }
}
