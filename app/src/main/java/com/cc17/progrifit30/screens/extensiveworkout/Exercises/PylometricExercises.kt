package com.cc17.progrifit30.screens.extensiveWorkout.Exercises

class PylometricExercises {
    companion object {
        val Exercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Box Jumps" to mapOf(
                "name" to "Box Jumps",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Tuck Jumps" to mapOf(
                "name" to "Tuck Jumps",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Clap Push-Ups" to mapOf(
                "name" to "Clap Push-Ups",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Skater Jumps" to mapOf(
                "name" to "Skater Jumps",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        fun getExercises(): List<String> {
            return Exercises.keys.toList()
        }

        fun getExercise(WorkoutLocation: String, Exercise: String): Array<String> {
            var name = ""
            var duration = ""
            var repetition = ""
            val unit = Exercises[Exercise]
            duration = unit?.get("duration").toString()
            repetition = unit?.get("repetitions").toString()
            name = unit?.get("name").toString()
            return arrayOf(name, duration, repetition)
        }
    }
}