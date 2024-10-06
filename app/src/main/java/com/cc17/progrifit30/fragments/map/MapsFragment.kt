package com.cc17.progrifit30.fragments.map

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.cc17.progrifit30.R
import com.cc17.progrifit30.db.model.UserRoutes
import com.cc17.progrifit30.db.userDB.UserRoutesViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mUserRoutesViewModel: UserRoutesViewModel
    private lateinit var sharedPref: SharedPreferences
    private lateinit var destinationLocation: LatLng

    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true

        var destinationMarker: Boolean = false

        mMap.setOnMapClickListener(object :GoogleMap.OnMapClickListener {
            override fun onMapClick(latlng :LatLng) {
                setupMap()
                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));
                destinationLocation = LatLng(latlng.latitude,latlng.longitude)

                if(destinationMarker) {
                    mMap.clear()
                    val currentLatLong = LatLng(lastLocation.latitude, lastLocation.longitude)
                    placeMarkerOnMap(currentLatLong)
                    mMap.addMarker(
                        MarkerOptions()
                            .position(destinationLocation)
                            .title("Destination"))
                }//check Marker Count
                else {
                    mMap.addMarker(
                        MarkerOptions()
                            .position(destinationLocation)
                            .title("Destination"))
                    destinationMarker = true
                }
            }
        })
    }

    private fun setupMap() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()){
            location ->
            if(location != null) {
                lastLocation = location
                val currentLatLong = LatLng(lastLocation.latitude, lastLocation.longitude)
                placeMarkerOnMap(currentLatLong)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))
            }
        }
    }

    private fun placeMarkerOnMap(currentLatLong: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("${currentLatLong}")
        mMap.addMarker(markerOptions)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        mUserRoutesViewModel = ViewModelProvider(this).get(UserRoutesViewModel::class)
        sharedPref = requireActivity().getSharedPreferences("route_name", Context.MODE_PRIVATE)

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (!isGranted) {
                Toast.makeText(requireContext(), "Please Allow Location Permission.", Toast.LENGTH_LONG).show()
            }
        }
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        view.findViewById<Button>(R.id.setcardio_confirm_route_button).setOnClickListener {
            addToDatabase(sharedPref.getString("route_name", "").toString(), lastLocation.latitude, lastLocation.longitude, destinationLocation.latitude, destinationLocation.longitude)
            view.findNavController().navigate(R.id.action_mapsFragment_to_listFragment)
        }
    }

    private fun addToDatabase(routeName: String, currentLat: Double, currentLong: Double, destLat: Double, destLong: Double ) {
        // Create Object
        var userRoutes = UserRoutes(0, routeName, currentLat, currentLong, destLat, destLong)

        // Add Data to Database
        mUserRoutesViewModel.insertUserRoutes(userRoutes)

        // Add user to sharedPref
        sharedPref.edit().putString("user_name", "").apply()
        Toast.makeText(requireContext(), "Registration Success!", Toast.LENGTH_LONG).show()
    }
}