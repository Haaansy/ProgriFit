package com.cc17.progrifit30.screens.resistanceWorkout.Exercises

class UpperBodyExercises {
    companion object {
        val ChestExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Barbell Bench Press" to mapOf(
                "name" to "Barbell Bench Press",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Dumbbell Bench Press" to mapOf(
                "name" to "Dumbbell Bench Press",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Incline Bench Press" to mapOf(
                "name" to "Incline Bench Press",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Chest Fly" to mapOf(
                "name" to "Chest Fly",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        val BackExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Deadlifts" to mapOf(
                "name" to "Deadlifts",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Pull-Ups" to mapOf(
                "name" to "Pull-Ups",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Barbell Rows" to mapOf(
                "name" to "Barbell Rows",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Lat Pulldown" to mapOf(
                "name" to "Lat Pulldown",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        val ShouldersExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Overhead Press" to mapOf(
                "name" to "Overhead Press",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Lateral Raises" to mapOf(
                "name" to "Lateral Raises",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Front Raises" to mapOf(
                "name" to "Front Raises",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        val ArmsExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Barbell Bicep Curl" to mapOf(
                "name" to "Barbell Bicep Curl",
                "duration" to 15,
                "repetitions" to 15,
            ),
            "Dumbbell Hammer Curl" to mapOf(
                "name" to "Dumbbell Hammer Curl",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Tricep Dips" to mapOf(
                "name" to "Tricep Dips",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Skull Crushers" to mapOf(
                "name" to "Tricep Dips",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        fun getChestExercises(): List<String> {
            return ChestExercises.keys.toList()
        }

        fun getBackExercises(): List<String> {
            return BackExercises.keys.toList()
        }

        fun getShouldersExercises(): List<String> {
            return ShouldersExercises.keys.toList()
        }

        fun getArmsExercises(): List<String> {
            return ArmsExercises.keys.toList()
        }

        fun getExercise(WorkoutLocation: String, Exercise: String): Array<String> {
            var name = ""
            var duration = ""
            var repetition = ""

            if(WorkoutLocation == "Chest") {
                val unit = ChestExercises[Exercise]
                duration = unit?.get("duration").toString()
                repetition = unit?.get("repetitions").toString()
                name = unit?.get("name").toString()
            } else if(WorkoutLocation == "Back") {
                val unit = BackExercises[Exercise]
                duration = unit?.get("duration").toString()
                repetition = unit?.get("repetitions").toString()
                name = unit?.get("name").toString()
            } else if(WorkoutLocation == "Shoulders") {
                val unit = ShouldersExercises[Exercise]
                duration = unit?.get("duration").toString()
                repetition = unit?.get("repetitions").toString()
                name = unit?.get("name").toString()
            } else if(WorkoutLocation == "Arms") {
                val unit = ArmsExercises[Exercise]
                duration = unit?.get("duration").toString()
                repetition = unit?.get("repetitions").toString()
                name = unit?.get("name").toString()
            }

            return arrayOf(name, duration, repetition)
        }
    }
}