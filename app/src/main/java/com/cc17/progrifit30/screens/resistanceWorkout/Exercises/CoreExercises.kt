package com.cc17.progrifit30.screens.resistanceWorkout.Exercises

class CoreExercises {
    companion object {
        val CoreExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Weighted Sit-Ups" to mapOf(
                "name" to "Weighted Sit-Ups",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Cable Woodchoppers" to mapOf(
                "name" to "Cable Woodchoppers",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Russian Twists" to mapOf(
                "name" to "Russian Twists",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Plank with Weight" to mapOf(
                "name" to "Plank with Weight",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        fun getCoreExercises(): List<String> {
            return CoreExercises.keys.toList()
        }

        fun getExercise(WorkoutLocation: String, Exercise: String): Array<String> {
            var name = ""
            var duration = ""
            var repetition = ""

            val unit = CoreExercises[Exercise]
            duration = unit?.get("duration").toString()
            repetition = unit?.get("repetitions").toString()
            name = unit?.get("name").toString()

            return arrayOf(name, duration, repetition)
        }
    }
}