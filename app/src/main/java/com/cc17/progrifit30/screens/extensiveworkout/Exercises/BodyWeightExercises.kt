package com.cc17.progrifit30.screens.extensiveWorkout.Exercises

class BodyWeightExercises {
    companion object {
        val Exercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Plank-to-Push-Up" to mapOf(
                "name" to "Plank-to-Push-Up",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Squat Jumps" to mapOf(
                "name" to "Squat Jumps",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Pistol Squats" to mapOf(
                "name" to "Pistol Squats",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Inchworms" to mapOf(
                "name" to "Inchworms",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Wall Sit" to mapOf(
                "name" to "Wall Sit",
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