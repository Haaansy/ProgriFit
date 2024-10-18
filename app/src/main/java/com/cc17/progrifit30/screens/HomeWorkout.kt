package com.cc17.progrifit30.screens

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cc17.progrifit30.R

class HomeWorkout : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var exerciseTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var startButton: Button // This will act as the Stop button now
    private lateinit var completeButton: Button
    private lateinit var imageView: ImageView

    private var exerciseIndex = 0
    private lateinit var currentExercise: Exercise
    private lateinit var timer: CountDownTimer

    private val exercises = mutableListOf(
        Exercise(
            "Jumping Jacks",
            "Jump up and spread your feet beyond hip-width apart while bringing your arms above your head, nearly touching.\n" +
                    "Jump again, lowering your arms and bringing your legs together. Return to your starting position.",
            5,
            "https://www.docteur-fitness.com/wp-content/uploads/2022/07/jumping-jack.gif"
        ),
        Exercise(
            "Mountain Climber",
            "Get into a plank position, distribute your weight evenly from your hands to your toes, keep your hands shoulder-width apart, back flat, and abs engaged," +
                    " then pull your right knee into your chest, " +
                    "switch legs by pulling the other knee in and extending the first, and maintain your hips down while alternating your knees in and out quickly, inhaling and exhaling with each leg change..",
            3,
            "https://gym-city.fr/wp-content/uploads/2022/09/mountain-climber-exercice-musculation.gif"
        ),
        Exercise(
            "Plank",
            "Start in a push-up position, then bend your elbows and rest your weight on your forearms. Hold this position for as long as you can.",
            6,
            "https://menspower.nl/wp-content/uploads/2018/02/plank.gif"
        ),
        Exercise(
            "Sit Ups",
            "Curl your torso towards your knees, using your abs, while keeping your feet and lower back on the ground.",
            3,
            "https://www.inspireusafoundation.org/wp-content/uploads/2023/08/raised-sit-up.gif"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homeworkout)

        titleTextView = findViewById(R.id.titleTextView)
        exerciseTextView = findViewById(R.id.exerciseTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        timerTextView = findViewById(R.id.timerTextView)
        startButton = findViewById(R.id.startButton) // Keep the same ID
        completeButton = findViewById(R.id.completeButton)
        imageView = findViewById(R.id.imageView)
        startButton.setOnClickListener {
            if (startButton.text == "Start Workout") {
                startWorkout()
            } else {
                stopWorkout() // Stop the workout when clicked after completion
            }
        }

        completeButton.setOnClickListener {
            completeExercise()
        }
    }

    private fun startWorkout() {
        exerciseIndex = 0
        titleTextView.text = "Workout Started"
        startButton.isEnabled = false
        startButton.text = "Stop Workout" // Change button text to "Stop Workout"
        startNextExercise()
    }

    private fun stopWorkout() {
        // Navigate back to the home page or reset the workout
        val intent = Intent(this, HomePage::class.java) // Replace with your home activity
        startActivity(intent)
        finish() // Optional: Finish this activity if you don't want it in the back stack
    }

    private fun completeExercise() {
        timer.cancel()
        completeButton.isEnabled = false
        startNextExercise()
    }

    private fun startNextExercise() {
        if (exerciseIndex < exercises.size) {
            currentExercise = exercises[exerciseIndex]
            exerciseTextView.text = currentExercise.name
            descriptionTextView.text = currentExercise.description
            Glide.with(this)
                .asGif()
                .load(currentExercise.gifImageUrl)
                .into(imageView)
            timerTextView.text = formatTime(currentExercise.durationInSeconds)

            timer = object : CountDownTimer(currentExercise.durationInSeconds * 1000L, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timerTextView.text = formatTime((millisUntilFinished / 1000).toInt())
                }

                override fun onFinish() {
                    timerTextView.text = "Exercise Complete"
                    imageView.visibility = ImageView.VISIBLE
                    completeButton.isEnabled = true
                }
            }.start()

            exerciseIndex++
        } else {
            exerciseTextView.text = "Workout Complete"
            descriptionTextView.text = ""
            timerTextView.text = ""
            completeButton.isEnabled = false

            // Change the startButton to "Start Again" after all exercises
            startButton.text = "Stop"
            startButton.isEnabled = true
        }
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}

data class Exercise(val name: String, val description: String, val durationInSeconds: Int, val gifImageUrl: String)