package com.example.lookup

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.lookup.databinding.ActivityMapBinding
import com.example.lookup.services.FlightDataService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding
    private lateinit var mapView: MapView
    private var flightService = FlightDataService()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding initialisieren
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // osmdroid-Konfiguration
        Configuration.getInstance().load(this, getSharedPreferences("osmdroid", MODE_PRIVATE))

        // initialize MapView
        mapView = binding.mapView
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(13.0)

        // Initialize Fused Location Provider
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getCurrentLocation()


        //add aircraft to the map
        addAirplanes()

        // Button-click-Listener
        binding.btnRefresh.setOnClickListener {
            Toast.makeText(this, "Refreshing aircraft data...", Toast.LENGTH_SHORT).show()
            mapView.overlays.clear()
            addAirplanes()
        }
    }

    // Check location permission and fetch current location
    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request location permission if not granted
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                addMarker(location.latitude, location.longitude, "You are here", R.drawable.circle)
                // Center the map on the user's location
                mapView.controller.setCenter(GeoPoint(location.latitude, location.longitude))
            }
        }
    }

    private fun addAirplanes() {
        flightService.getFlightData { flightList ->
            flightList.forEach { flight ->
                addMarker(flight.latitude, flight.longitude, flight.callSign, R.drawable.airplan)
            }
        }
    }

    private fun addMarker(lat: Double, lon: Double, title: String, icon: Int) {
        val marker = Marker(mapView)
        marker.position = org.osmdroid.util.GeoPoint(lat, lon)
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = title
        marker.icon = ContextCompat.getDrawable(this, icon)
        mapView.overlays.add(marker)
    }


    override fun onResume() {
        super.onResume()
        mapView.onResume() // Kartenansicht wieder aufnehmen
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause() // Kartenansicht pausieren
    }
}
