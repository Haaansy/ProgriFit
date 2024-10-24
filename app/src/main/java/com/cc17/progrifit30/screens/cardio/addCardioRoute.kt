package com.cc17.progrifit30.screens.cardio

import android.Manifest
import com.cc17.progrifit30.R
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.cc17.progrifit30.db.TypeConverter.Converters
import com.cc17.progrifit30.db.model.UserRoutes
import com.cc17.progrifit30.db.userDB.UserRoutesViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.textfield.TextInputLayout
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.viewport


class addCardioRoute : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapView: MapView
    private lateinit var mUserRoutesViewModel: UserRoutesViewModel
    private lateinit var permissionsManager: PermissionsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_route)

        // UserRoutesViewModel
        mUserRoutesViewModel = ViewModelProvider(this).get(UserRoutesViewModel::class.java)

        // Initializing FusedLocation
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Permission Listener
        var permissionsListener: PermissionsListener = object : PermissionsListener {
            override fun onExplanationNeeded(permissionsToExplain: List<String>) {

            }

            override fun onPermissionResult(granted: Boolean) {
                if (granted) {

                    // Permission sensitive logic called here, such as activating the Maps SDK's LocationComponent to show the device's location

                } else {

                    // User denied the permission

                }
            }
        }



        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            // Initializing Mapbox
            mapView = findViewById(R.id.myMap)

            // Mapbox Options
            with(mapView) {
                location.locationPuck = createDefault2DPuck(withBearing = true)
                location.enabled = true
                location.puckBearing = PuckBearing.COURSE
                location.puckBearingEnabled = true
                viewport.transitionTo(
                    targetState = viewport.makeFollowPuckViewportState(),
                    transition = viewport.makeImmediateViewportTransition()
                )
            }

            // Start Button
            val startButton = findViewById<Button>(R.id.start_route_button)
            startButton.setOnClickListener {
                addToDataBase()
            }

        } else {
            permissionsManager = PermissionsManager(permissionsListener)
            permissionsManager.requestLocationPermissions(this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    // Function to get Values from EditText and Call Check for input before adding to database
    private fun addToDataBase() {
        val routeName = findViewById<TextInputLayout>(R.id.route_name_input).editText?.text.toString()

        if (inputCheck(routeName)) {
            // Retrieve the existing UserRoutes entry (or create a new one if needed)
            val userRoutes = UserRoutes(id = 0, name = "User", points = "")

            // Add Data to Database
            mUserRoutesViewModel.insertUserRoutes(userRoutes)

            // Proceed to Start Navigation
            val intent = Intent(this@addCardioRoute, StartRun::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please fill up all fields.", Toast.LENGTH_LONG).show()
        }
    }

    // Check Input Function [ CHECK EMPTY FIELDS ]
    private fun inputCheck(routename: String): Boolean {
        return !(routename.isEmpty())
    }
}
