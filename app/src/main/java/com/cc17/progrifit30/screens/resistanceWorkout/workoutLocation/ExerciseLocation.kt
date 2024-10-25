package com.cc17.progrifit30.screens.resistanceWorkout.workoutLocation

class ExerciseLocation {
    companion object {
        val UpperBodyResistanceExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Chest" to mapOf(
                "name" to "Chest",
            ),
            "Back" to mapOf(
                "name" to "Back",
            ),
            "Shoulders" to mapOf(
                "name" to "Shoulders",
            ),
            "Arms" to mapOf(
                "name" to "Arms",
            )
        )

        val LowerBodyResistanceExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Quads and Hamstrings" to mapOf(
                "name" to "Quads and Hamstrings",
            ),
            "Glutes" to mapOf(
                "name" to "Glutes",
            ),
            "Calves" to mapOf(
                "name" to "Calves",
            )
        )

        fun getUpperBodyResistanceExercises(): List<String> {
            return UpperBodyResistanceExercises.keys.toList()
        }

        fun getLowerBodyResistanceExercises(): List<String> {
            return LowerBodyResistanceExercises.keys.toList()
        }
    }
}