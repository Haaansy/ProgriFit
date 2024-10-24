package com.cc17.progrifit30.screens.extensiveWorkout.Exercises

class FlexibilityExercises {
    companion object {
        val Exercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Dynamic Stretches" to mapOf(
                "name" to "Dynamic Stretches",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Yoga Poses" to mapOf(
                "name" to "Yoga Poses",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Hamstring Stretch" to mapOf(
                "name" to "Hamstring Stretch",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Shoulder Stretch" to mapOf(
                "name" to "Cat-Cow Stretch",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Hip Flexor Stretch" to mapOf(
                "name" to "Hip Flexor Stretch",
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