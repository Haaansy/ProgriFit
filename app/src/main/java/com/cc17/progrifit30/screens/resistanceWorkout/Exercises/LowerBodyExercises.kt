package com.cc17.progrifit30.screens.resistanceWorkout.Exercises

class LowerBodyExercises {
    companion object {
        val QuadsExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Squats" to mapOf(
                "name" to "Squats",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Leg Press" to mapOf(
                "name" to "Leg Press",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Lunges" to mapOf(
                "name" to "Lunges",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Romanian Deadlifts" to mapOf(
                "name" to "Romanian Deadlifts",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        val GlutesExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Hip Thrusts" to mapOf(
                "name" to "Hip Thrusts",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Glute Bridges" to mapOf(
                "name" to "Glute Bridges",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Barbell Rows" to mapOf(
                "name" to "Barbell Rows",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Step-Ups" to mapOf(
                "name" to "Step-Ups",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        val CalvesExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Standing Calf Raises" to mapOf(
                "name" to "Standing Calf Raises",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Seated Calf Raises" to mapOf(
                "name" to "Seated Calf Raises",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        fun getQuadsExercise(): List<String> {
            return QuadsExercises.keys.toList()
        }

        fun getGlutesExercise(): List<String> {
            return GlutesExercises.keys.toList()
        }

        fun getCalvesExercise(): List<String> {
            return CalvesExercises.keys.toList()
        }

        fun getExercise(WorkoutLocation: String, Exercise: String): Array<String> {
            var name = ""
            var duration = ""
            var repetition = ""

            if(WorkoutLocation == "Quads and Hamstrings") {
                val unit = QuadsExercises[Exercise]
                duration = unit?.get("duration").toString()
                repetition = unit?.get("repetitions").toString()
                name = unit?.get("name").toString()
            } else if(WorkoutLocation == "Glutes") {
                val unit = GlutesExercises[Exercise]
                duration = unit?.get("duration").toString()
                repetition = unit?.get("repetitions").toString()
                name = unit?.get("name").toString()
            } else if(WorkoutLocation == "Calves") {
                val unit = CalvesExercises[Exercise]
                duration = unit?.get("duration").toString()
                repetition = unit?.get("repetitions").toString()
                name = unit?.get("name").toString()
            }

            return arrayOf(name, duration, repetition)
        }
    }
}