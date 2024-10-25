package com.cc17.progrifit30.screens.resistanceWorkout.Exercises

import com.cc17.progrifit30.screens.extensiveWorkout.Exercises.FullBodyExercises

class BodyWeightExercises {
    companion object {
        val BodyWeightExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Push-Ups" to mapOf(
                "name" to "Push-Ups",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Pull-Ups/Chin-Ups" to mapOf(
                "name" to "Pull-Ups/Chin-Ups",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Bodyweight Squats" to mapOf(
                "name" to "Bodyweight Squats",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Dips" to mapOf(
                "name" to "Dips",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        fun getBodyWeightExercises(): List<String> {
            return BodyWeightExercises.keys.toList()
        }

        fun getExercise(WorkoutLocation: String, Exercise: String): Array<String> {
            var name = ""
            var duration = ""
            var repetition = ""

            val unit = BodyWeightExercises[Exercise]
            duration = unit?.get("duration").toString()
            repetition = unit?.get("repetitions").toString()
            name = unit?.get("name").toString()

            return arrayOf(name, duration, repetition)
        }
    }
}