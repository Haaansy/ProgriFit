package com.cc17.progrifit30.screens.cardio

import com.cc17.progrifit30.R
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import com.cc17.progrifit30.db.model.User
import com.cc17.progrifit30.db.model.UserRoutes
import com.cc17.progrifit30.db.userDB.UserRoutesViewModel
import com.cc17.progrifit30.db.userDB.UserViewModel
import com.cc17.progrifit30.screens.HomePage
import com.cc17.progrifit30.screens.InformationPage
import com.google.android.gms.location.*
import com.google.android.material.textfield.TextInputLayout
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.viewport
import com.mapbox.navigation.core.MapboxNavigation
import org.w3c.dom.Text


class addCardioRoute : AppCompatActivity() {

    companion object {
        private var currLatitude = 16.376068   // Temporary * Change to 0.0 in Production *
        private var currLongitude = 120.576133 // Temporary * Change to 0.0 in Production *
        private var destLatitude = 0.0
        private var destLongitude = 0.0

        fun modifyCurr(lat: Double, long: Double){
            currLongitude = long
            currLatitude = lat
        }

        fun modifyDest(lat: Double, long: Double){
            destLongitude = long
            destLatitude = lat
        }

        fun getCurrentLatitude(): Double{
            return currLatitude
        }

        fun getCurrentLongitude(): Double{
            return currLongitude
        }

        fun getDestinationLatitude(): Double{
            return destLatitude
        }

        fun getDestinationLongitude(): Double{
            return destLongitude
        }
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapView: MapView
    private lateinit var pointAnnotationManager: PointAnnotationManager
    private lateinit var mUserRoutesViewModel: UserRoutesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_route)

        mUserRoutesViewModel = ViewModelProvider(this).get(UserRoutesViewModel::class.java)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        mapView = findViewById(R.id.myMap)

        with(mapView) {
            location.locationPuck = createDefault2DPuck(withBearing = true)
            location.enabled = true
            location.puckBearing = PuckBearing.COURSE
            location.puckBearingEnabled = true
            viewport.transitionTo(
                targetState = viewport.makeFollowPuckViewportState(),
                transition = viewport.makeImmediateViewportTransition()
            )
            mapView.mapboxMap.loadStyle(Style.DARK) {
                pointAnnotationManager = annotations.createPointAnnotationManager()
                mapView.mapboxMap.addOnMapClickListener { point ->
                    addMarker(point)
                    val destlatitude = point.latitude()
                    val destlongitude = point.longitude()

                    val companion = Companion
                    companion.modifyDest(destlatitude, destlongitude)

                    Toast.makeText(this@addCardioRoute, "Clicked Location: $destlatitude, $destlongitude", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }

        // Get the last known location
        getLastKnownLocation()

        val confirmButton = findViewById<Button>(R.id.add_route_button)
        confirmButton.setOnClickListener {
            addToDataBase()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val currentlatitude = location.latitude
                    val currentlongitude = location.longitude

                    val companion = Companion
                    companion.modifyCurr(currentlatitude, currentlongitude)
                    Toast.makeText(this, "Current Location: $currentlatitude, $currentlongitude", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Location not available", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error getting location: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addMarker(point: Point) {
        pointAnnotationManager.deleteAll()

        val markerOptions = PointAnnotationOptions()
            .withPoint(point)
            .withIconImage(getDrawable(R.drawable.red_marker)?.toBitmap()!!) // Ensure the marker icon is present
            .withIconSize(0.025)

        // Add the marker to the map
        pointAnnotationManager.create(markerOptions)

        // Optionally, you can log or display the point coordinates
        Toast.makeText(this, "Marker added at: ${point.latitude()}, ${point.longitude()}", Toast.LENGTH_SHORT).show()
    }

    // Function to get Values from EditText and Call Check for input before adding to database
    private fun addToDataBase() {
        val routeName = findViewById<TextInputLayout>(R.id.route_name_input).editText?.text.toString()

        // Companion Object
        val companion = Companion
        val currentLatitude = companion.currLatitude
        val currentLongitude = companion.currLongitude
        val destLatitude = companion.destLatitude
        val destLongitude = companion.destLongitude

        if (inputCheck(routeName, currentLatitude, currentLongitude, destLatitude, destLongitude)) {
            // Create User Object
            val userRoutes = UserRoutes(
                0,
                routeName,
                companion.getCurrentLatitude(),
                companion.getCurrentLongitude(),
                companion.getDestinationLatitude(),
                companion.getDestinationLongitude(),
            )

            // Add Data to Database
            mUserRoutesViewModel.insertUserRoutes(userRoutes)

            val intent = Intent(this@addCardioRoute, CardioTrainingPage::class.java)
            startActivity(intent)

        } else {
            Toast.makeText(this, "Please fill up all fields.", Toast.LENGTH_LONG).show()
        }
    }

    // Check Input Function [ CHECK EMPTY FIELDS ]
    private fun inputCheck(routename: String, currentLatitude: Double, currentLongitude: Double, destLatitude: Double, destLongitude: Double ): Boolean {
        return !(routename.isEmpty() || currentLatitude == null || currentLongitude == null || destLatitude == null || destLongitude == null)
    }
}
