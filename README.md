# FitPals - Android Workout Tracker

A native Android application for tracking your workouts, including exercises, sets, reps, and weights. The app stores all data locally using SQLite database.

## Features
- Add workouts with exercise name, sets, reps, and weight
- View workout history in chronological order
- Material Design UI with dark mode support
- Works offline - no internet connection required
- Responsive layout (supports both portrait and landscape)

## Project Setup

### Prerequisites
- Android Studio (latest version recommended)
- JDK 17 or higher
- Android SDK with minimum API level 24 (Android 7.0)

### How to Run the Project

1. Clone or download this repository to your local machine

2. Open Android Studio

3. Click on "Open an existing Android Studio project"

4. Navigate to the downloaded project folder and select it

5. Wait for Android Studio to complete the project sync and Gradle build

6. Once the sync is complete, you can run the app by:
   - Connecting an Android device via USB (with USB debugging enabled)
   - Or using an Android Emulator
   - Click the "Run" button (green play icon) or press Shift + F10

### Project Structure
```
app/
├── build.gradle                 # App level Gradle build file
├── src/
    ├── main/
        ├── AndroidManifest.xml  # App manifest file
        ├── java/
        │   └── com/example/fitpals/
        │       ├── MainActivity.java
        │       ├── Workout.java
        │       ├── WorkoutAdapter.java
        │       ├── WorkoutDao.java
        │       ├── WorkoutDatabase.java
        │       ├── WorkoutRepository.java
        │       ├── WorkoutViewModel.java
        │       └── DateConverter.java
        └── res/
            ├── drawable/
            │   └── weight_background.xml
            ├── layout/
            │   ├── activity_main.xml
            │   └── workout_item.xml
            ├── values/
            │   ├── colors.xml
            │   ├── strings.xml
            │   └── themes.xml
            └── values-night/
                └── themes.xml
```

### Troubleshooting

If you encounter any build issues:
1. Go to File -> Invalidate Caches / Restart
2. Clean Project (Build -> Clean Project)
3. Rebuild Project (Build -> Rebuild Project)

## Technical Details

- Language: Java
- Architecture: MVVM (Model-View-ViewModel)
- Database: Room (SQLite)
- UI Components: Material Design
- Minimum SDK: API 24 (Android 7.0)
- Target SDK: API 34 (Android 14)

## Dependencies

The app uses the following major dependencies:
- AndroidX Core and AppCompat
- Material Design Components
- Room Database
- ViewModel and LiveData
- ConstraintLayout

All dependencies are managed through Gradle and will be automatically downloaded when you build the project.
