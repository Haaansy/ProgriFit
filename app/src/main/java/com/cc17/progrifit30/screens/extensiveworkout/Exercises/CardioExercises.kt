package com.cc17.progrifit30.screens.extensiveWorkout.Exercises

class CardioExercises {
    companion object {
        val Exercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Jumping Jacks" to mapOf(
                "name" to "Jumping Jacks",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Burpees" to mapOf(
                "name" to "Burpees",
                "duration" to 20,
                "repetitions" to 15
            ),
            "High Knees" to mapOf(
                "name" to "High Knees",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Sprinting" to mapOf(
                "name" to "Sprinting",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Skipping Rope" to mapOf(
                "name" to "Skipping Rope",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Running in Place" to mapOf(
                "name" to "Running in Place",
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