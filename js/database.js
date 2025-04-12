class Database {
    constructor() {
        this.dbName = 'fitpalsDB';
        this.dbVersion = 1;
        this.storeName = 'workouts';
        this.db = null;
        this.initDatabase();
    }

    async initDatabase() {
        try {
            return new Promise((resolve, reject) => {
                const request = indexedDB.open(this.dbName, this.dbVersion);

                request.onerror = () => {
                    console.error('Failed to open database');
                    reject(request.error);
                };

                request.onsuccess = () => {
                    this.db = request.result;
                    resolve();
                };

                request.onupgradeneeded = (event) => {
                    const db = event.target.result;
                    if (!db.objectStoreNames.contains(this.storeName)) {
                        db.createObjectStore(this.storeName, { 
                            keyPath: 'id', 
                            autoIncrement: true 
                        });
                    }
                };
            });
        } catch (err) {
            console.error('Failed to initialize database:', err);
        }
    }

    async addWorkout(workout) {
        try {
            return new Promise((resolve, reject) => {
                const transaction = this.db.transaction([this.storeName], 'readwrite');
                const store = transaction.objectStore(this.storeName);
                
                const workoutData = {
                    ...workout,
                    date: new Date().toISOString()
                };
                
                const request = store.add(workoutData);
                
                request.onsuccess = () => resolve(true);
                request.onerror = () => reject(request.error);
            });
        } catch (err) {
            console.error('Failed to add workout:', err);
            return false;
        }
    }

    async getWorkouts() {
        try {
            return new Promise((resolve, reject) => {
                const transaction = this.db.transaction([this.storeName], 'readonly');
                const store = transaction.objectStore(this.storeName);
                const request = store.getAll();
                
                request.onsuccess = () => {
                    resolve(request.result.sort((a, b) => 
                        new Date(b.date) - new Date(a.date)
                    ));
                };
                
                request.onerror = () => {
                    console.error('Failed to get workouts:', request.error);
                    reject([]);
                };
            });
        } catch (err) {
            console.error('Failed to get workouts:', err);
            return [];
        }
    }
}
