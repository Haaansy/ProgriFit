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

class ExtensiveWorkout : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var workoutTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var nextButton: Button
    private lateinit var completeButton: Button
    private lateinit var stopButton: Button
    private lateinit var imageView: ImageView
    private lateinit var startButton: Button // Add this line

    private var workoutIndex = 0
    private lateinit var currentWorkout: Workout
    private lateinit var timer: CountDownTimer

    private val workouts = mutableListOf(
        Workout("Dumbbell Curls", "Stand with a dumbbell in each hand, arms at your sides. Curl the weights up towards your shoulders, keeping your elbows close to your torso.", 3, "https://v4excellencefitness.com.br/wp-content/uploads/2023/05/biceps-curl-haltere.gif"),
        Workout("Lateral Raise", "Stand with a dumbbell in each hand at your sides. Raise the weights out to the sides until they are shoulder height, then lower them back down.", 3, "https://homeworkouts.org/wp-content/uploads/anim-dumbbell-lateral-raise.gif"),
        Workout("Bench Press", "Lie on a bench and press a barbell or dumbbells up from your chest until your arms are extended. Lower the weight back to your chest.", 3, "https://www.inspireusafoundation.org/wp-content/uploads/2022/04/barbell-bench-press.gif"),
        Workout("Shoulder Shrug", "Stand with a dumbbell in each hand at your sides. Raise your shoulders towards your ears, then lower them back down.", 3, "http://newlife.com.cy/wp-content/uploads/2019/11/04061301-Dumbbell-Shrug_Back-FIX_360.gif"),
        Workout("Incline Bench Press", "Lie on an incline bench and press a barbell or dumbbells up from your chest until your arms are extended. Lower the weight back to your chest.", 3, "https://www.fitadium.com/conseils/wp-content/uploads/2020/08/00471301-Barbell-Incline-Bench-Press_Chest_720.gif"),
        Workout("Seated Cable Row", "Sit at a cable row machine, grasp the handle, and pull it towards your torso, keeping your back straight.", 3, "https://www.inspireusafoundation.org/wp-content/uploads/2023/04/cable-underhand-row.gif")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extensive_workout)

        titleTextView = findViewById(R.id.titleTextView)
        workoutTextView = findViewById(R.id.workout)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        timerTextView = findViewById(R.id.timerTextView)
        nextButton = findViewById(R.id.next)
        completeButton = findViewById(R.id.completeButton)
        stopButton = findViewById(R.id.stop)
        imageView = findViewById(R.id.imageView)
        startButton = findViewById(R.id.startButton) // Initialize startButton

        startButton.setOnClickListener {
            startWorkout() // Start workout on button click
        }

        nextButton.setOnClickListener {
            if (workoutIndex < workouts.size) {
                completeWorkout()
            }
        }

        completeButton.setOnClickListener {
            completeWorkout()
        }

        stopButton.setOnClickListener {
            stopWorkout()
        }
    }

    private fun startWorkout() {
        workoutIndex = 0
        titleTextView.text = "Workout Started"
        nextButton.isEnabled = false
        completeButton.isEnabled = false
        stopButton.isEnabled = false
        startNextWorkout()
    }

    private fun completeWorkout() {
        timer.cancel()
        completeButton.isEnabled = false
        nextButton.isEnabled = true
    }

    private fun startNextWorkout() {
        if (workoutIndex < workouts.size) {
            currentWorkout = workouts[workoutIndex]
            workoutTextView.text = currentWorkout.name
            descriptionTextView.text = currentWorkout.description
            Glide.with(this)
                .asGif()
                .load(currentWorkout.gifImageUrl)
                .into(imageView)
            timerTextView.text = formatTime(currentWorkout.durationInSeconds)

            timer = object : CountDownTimer(currentWorkout.durationInSeconds * 1000L, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timerTextView.text = formatTime((millisUntilFinished / 1000).toInt())
                }

                override fun onFinish() {
                    timerTextView.text = "Workout Complete"
                    imageView.visibility = ImageView.VISIBLE
                    completeButton.isEnabled = true
                }
            }.start()

            workoutIndex++
        } else {
            workoutTextView.text = "All Workouts Complete"
            descriptionTextView.text = ""
            timerTextView.text = ""
            nextButton.isEnabled = false
            completeButton.isEnabled = false
            stopButton.isEnabled = true // Enable the stop button
        }
    }

    private fun stopWorkout() {
        // Navigate back to the home page or reset the workout
        val intent = Intent(this, HomePage::class.java) // Replace with your home activity
        startActivity(intent)
        finish() // Optional: Finish this activity if you don't want it in the back stack
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}

data class Workout(val name: String, val description: String, val durationInSeconds: Int, val gifImageUrl: String)