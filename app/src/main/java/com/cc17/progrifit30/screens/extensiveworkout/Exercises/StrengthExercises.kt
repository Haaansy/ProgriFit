package com.cc17.progrifit30.screens.extensiveWorkout.Exercises

class StrengthExercises {
    companion object {
        val UpperBodyExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Push-Ups" to mapOf(
                "name" to "Push-Ups",
                "duration" to 20,
                "repetitions" to 15,
            ),
            "Pull-Ups" to mapOf(
                "name" to "Pull-Ups",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Dumbbell Bench Press" to mapOf(
                "name" to "Dumbbell Bench Press",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Bent-Over Rows" to mapOf(
                "name" to "Bent-Over Rows",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Shoulder Press" to mapOf(
                "name" to "Shoulder Press",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Bicep Curls" to mapOf(
                "name" to "Bicep Curls",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Tricep Dips" to mapOf(
                "name" to "Tricep Dips",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        val LowerBodyExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Squats" to mapOf(
                "name" to "Squats",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Lunges" to mapOf(
                "name" to "Lunges",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Deadlifts" to mapOf(
                "name" to "Deadlifts",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Step-Ups" to mapOf(
                "name" to "Step-Ups",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Leg Press" to mapOf(
                "name" to "Leg Press",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Calf Raises" to mapOf(
                "name" to "Calf Raises",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        val CoreExercises = mapOf( // MODIFY EXERCISES FOR REPS AND DURATION
            "Planks" to mapOf(
                "name" to "Planks",
                "duration" to 20,
                "repetitions" to 0
            ),
            "Crunches" to mapOf(
                "name" to "Crunches",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Leg Raises" to mapOf(
                "name" to "Leg Raises",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Russian Twist" to mapOf(
                "name" to "Russian Twist",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Bicycle Crunches" to mapOf(
                "name" to "Bicycle Crunches",
                "duration" to 20,
                "repetitions" to 15
            ),
            "Mountain Climbers" to mapOf(
                "name" to "Mountain Climbers",
                "duration" to 20,
                "repetitions" to 15
            ),
        )

        fun getUpperBodyExerciseNames(): List<String> {
            return UpperBodyExercises.keys.toList()
        }

        fun getLowerBodyExerciseNames(): List<String> {
            return LowerBodyExercises.keys.toList()
        }

        fun getCoreExerciseNames(): List<String> {
            return CoreExercises.keys.toList()
        }

        fun getExercise(WorkoutLocation: String, Exercise: String): Array<String> {
            var name = ""
            var duration = ""
            var repetition = ""
            if(WorkoutLocation == "Upper Body"){
                val unit = UpperBodyExercises[Exercise]
                duration = unit?.get("duration").toString()
                repetition = unit?.get("repetitions").toString()
                name = unit?.get("name").toString()
            } else if(WorkoutLocation == "Lower Body"){
                val unit = LowerBodyExercises[Exercise]
                duration = unit?.get("duration").toString()
                repetition = unit?.get("repetitions").toString()
                name = unit?.get("name").toString()
            } else if(WorkoutLocation == "Core"){
                val unit = CoreExercises[Exercise]
                duration = unit?.get("duration").toString()
                repetition = unit?.get("repetitions").toString()
                name = unit?.get("name").toString()
            }
            return arrayOf(name, duration, repetition)
        }
    }
}