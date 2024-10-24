package com.cc17.progrifit30.screens.extensiveworkout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cc17.progrifit30.R
import com.cc17.progrifit30.screens.extensiveWorkout.Exercises.BodyWeightExercises
import com.cc17.progrifit30.screens.extensiveWorkout.Exercises.CardioExercises
import com.cc17.progrifit30.screens.extensiveWorkout.Exercises.FlexibilityExercises
import com.cc17.progrifit30.screens.extensiveWorkout.Exercises.FullBodyExercises
import com.cc17.progrifit30.screens.extensiveWorkout.Exercises.PylometricExercises
import com.cc17.progrifit30.screens.extensiveWorkout.Exercises.StrengthExercises

class StartTraining: AppCompatActivity() {

    private lateinit var exerciseInfo: Array<String>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ext_start_training)

        val workoutType = getSharedPreferences("workout_type", Context.MODE_PRIVATE)
        val workoutLocation = getSharedPreferences("workout_location", Context.MODE_PRIVATE)
        val workoutName = getSharedPreferences("workout_name", Context.MODE_PRIVATE)

        val workoutVal = workoutType.getString("workout_type", "").toString()
        val workoutLoc = workoutLocation.getString("workout_location", "").toString()
        val workoutExerciseName = workoutName.getString("workout_name", "").toString()

        val headerText = findViewById<TextView>(R.id.workout_type_text)
        val subheaderText = findViewById<TextView>(R.id.workout_location_text)
        val repetitionsText = findViewById<TextView>(R.id.repetitions_label)
        val timeText = findViewById<TextView>(R.id.time_label)
        val nameText = findViewById<TextView>(R.id.workout_name_label)
        val completeButton = findViewById<Button>(R.id.start_workout_complete_button)
        val startButton = findViewById<Button>(R.id.start_workout_button)

        // Set Header and Sub Header Text
        headerText.text = workoutVal

        // Get Exercise

        if(workoutVal == "Strength Training") {
            subheaderText.text = workoutLoc
            exerciseInfo = StrengthExercises.getExercise(workoutLoc, workoutExerciseName)
        } else if(workoutVal == "Cardio Exercises") {
            subheaderText.text = ""
            exerciseInfo = CardioExercises.getExercise(workoutLoc, workoutExerciseName)
        } else if(workoutVal == "Flexibility & Mobility") {
            subheaderText.text = ""
            exerciseInfo = FlexibilityExercises.getExercise(workoutLoc, workoutExerciseName)
        } else if(workoutVal == "Full-Body Workouts") {
            subheaderText.text = ""
            exerciseInfo = FullBodyExercises.getExercise(workoutLoc, workoutExerciseName)
        } else if(workoutVal == "Body Weight Exercises") {
            subheaderText.text = ""
            exerciseInfo = BodyWeightExercises.getExercise(workoutLoc, workoutExerciseName)
        } else if(workoutVal == "Pylometric") {
            subheaderText.text = ""
            exerciseInfo = PylometricExercises.getExercise(workoutLoc, workoutExerciseName)
        }

        // Unpack the returned array
        if (exerciseInfo != null) {
            val name = exerciseInfo[0]
            var duration = exerciseInfo[1].toInt()
            val repetitions = exerciseInfo[2].toInt()

            // use the exercise data
            timeText.text = duration.toString()
            repetitionsText.text = repetitions.toString()
            nameText.text = name

            startButton.setOnClickListener {
                startButton.isClickable = false
                completeButton.isClickable = false
                startButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Primary)
                completeButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Primary)

                val handler = android.os.Handler(Looper.getMainLooper()) // Ensure it's running on the main thread

                val runnable = object : Runnable {
                    override fun run() {
                        if (duration > 0) {
                            duration -= 1
                            timeText.text = duration.toString()
                            handler.postDelayed(this, 1000) // Delay by 1 second
                        } else {
                            startButton.isClickable = true
                            completeButton.isClickable = true
                            startButton.backgroundTintList = ContextCompat.getColorStateList(this@StartTraining, R.color.Secondary)
                            completeButton.backgroundTintList = ContextCompat.getColorStateList(this@StartTraining, R.color.Secondary)
                        }
                    }
                }
                handler.post(runnable)
            }

            completeButton.setOnClickListener {
                finish()
                val intent = Intent(this@StartTraining, SelectTraining::class.java)
                startActivity(intent)
            }
        }
    }
}
