package com.cc17.progrifit30.screens.resistanceWorkout.Exercises

import com.cc17.progrifit30.screens.extensiveWorkout.Exercises.FullBodyExercises

class FullBodyExercises {
    companion object {
        val FullBodyExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Clean and Press" to mapOf(
                "name" to "Clean and Press",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Kettlebell Swings" to mapOf(
                "name" to "Cable Kettlebell Swings",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Thrusters" to mapOf(
                "name" to "Russian Twists",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        fun getFullBodyExercises(): List<String> {
            return FullBodyExercises.keys.toList()
        }

        fun getExercise(WorkoutLocation: String, Exercise: String): Array<String> {
            var name = ""
            var duration = ""
            var repetition = ""

            val unit = FullBodyExercises[Exercise]
            duration = unit?.get("duration").toString()
            repetition = unit?.get("repetitions").toString()
            name = unit?.get("name").toString()

            return arrayOf(name, duration, repetition)
        }
    }
}