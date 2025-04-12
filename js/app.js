class FitPalsApp {
    constructor() {
        this.database = new Database();
        this.workoutForm = document.getElementById('workoutForm');
        this.workoutList = document.getElementById('workoutList');
        this.toast = document.getElementById('toast');
        this.toastMessage = document.getElementById('toastMessage');
        
        this.initializeEventListeners();
    }

    initializeEventListeners() {
        // Handle form submission
        this.workoutForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            await this.handleWorkoutSubmit();
        });

        // Load workouts when database is ready
        this.database.initDatabase().then(() => {
            this.loadWorkouts();
        });
    }

    async handleWorkoutSubmit() {
        const workout = {
            exerciseName: document.getElementById('exerciseName').value,
            sets: parseInt(document.getElementById('sets').value),
            reps: parseInt(document.getElementById('reps').value),
            weight: parseFloat(document.getElementById('weight').value)
        };

        try {
            const success = await this.database.addWorkout(workout);
            if (success) {
                this.showToast('Workout added successfully!');
                this.workoutForm.reset();
                await this.loadWorkouts();
            } else {
                this.showToast('Failed to add workout', 'error');
            }
        } catch (err) {
            console.error('Error adding workout:', err);
            this.showToast('Failed to add workout', 'error');
        }
    }

    async loadWorkouts() {
        try {
            const workouts = await this.database.getWorkouts();
            this.workoutList.innerHTML = '';

            if (workouts.length === 0) {
                this.workoutList.innerHTML = `
                    <div class="text-center text-gray-500 py-8">
                        <i class="fas fa-dumbbell text-4xl mb-4"></i>
                        <p>No workouts yet. Start by adding one!</p>
                    </div>
                `;
                return;
            }

            workouts.forEach(workout => {
                const workoutCard = this.createWorkoutCard(workout);
                this.workoutList.appendChild(workoutCard);
            });
        } catch (err) {
            console.error('Error loading workouts:', err);
            this.showToast('Failed to load workouts', 'error');
        }
    }

    createWorkoutCard(workout) {
        const date = new Date(workout.date).toLocaleDateString();
        const card = document.createElement('div');
        card.className = 'bg-gray-50 rounded-lg p-4 shadow-sm hover:shadow-md transition duration-200 mb-4';
        
        card.innerHTML = `
            <div class="flex justify-between items-start">
                <div>
                    <h3 class="text-xl font-semibold text-gray-800">${workout.exerciseName}</h3>
                    <p class="text-gray-600 text-sm">${date}</p>
                </div>
                <div class="bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm">
                    ${workout.weight} kg
                </div>
            </div>
            <div class="mt-4 grid grid-cols-2 gap-4">
                <div class="text-center p-2 bg-gray-100 rounded">
                    <p class="text-gray-600 text-sm">Sets</p>
                    <p class="text-lg font-semibold">${workout.sets}</p>
                </div>
                <div class="text-center p-2 bg-gray-100 rounded">
                    <p class="text-gray-600 text-sm">Reps</p>
                    <p class="text-lg font-semibold">${workout.reps}</p>
                </div>
            </div>
        `;

        return card;
    }

    showToast(message, type = 'success') {
        this.toastMessage.textContent = message;
        this.toast.className = `fixed bottom-4 right-4 px-6 py-3 rounded-lg shadow-lg ${
            type === 'success' ? 'bg-green-500' : 'bg-red-500'
        } text-white`;
        
        this.toast.classList.remove('hidden');
        setTimeout(() => {
            this.toast.classList.add('hidden');
        }, 3000);
    }
}

// Initialize the app
const app = new FitPalsApp();
