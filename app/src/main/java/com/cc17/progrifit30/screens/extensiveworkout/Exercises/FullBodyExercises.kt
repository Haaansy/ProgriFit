package com.cc17.progrifit30.screens.extensiveWorkout.Exercises

class FullBodyExercises {
    companion object {
        val Exercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Kettlebell Swings" to mapOf(
                "name" to "Kettlebell Swings",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Thrusters" to mapOf(
                "name" to "Thrusters",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Clean and Press" to mapOf(
                "name" to "Clean and Press",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Shoulder Stretch" to mapOf(
                "name" to "Cat-Cow Stretch",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Bear Crawl" to mapOf(
                "name" to "Bear Crawl",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Farmer’s Walk" to mapOf(
                "name" to "Farmer’s Walk",
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