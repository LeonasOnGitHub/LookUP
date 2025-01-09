package com.example.lookup

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
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
                addMarker(flight.latitude, flight.longitude, flight.id, R.drawable.airplan)
            }
        }
    }

    private fun addMarker(lat: Double, lon: Double, id: String, icon: Int) {
        val marker = Marker(mapView)
        marker.position = org.osmdroid.util.GeoPoint(lat, lon)
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = id
        marker.icon = ContextCompat.getDrawable(this, icon)
        marker.setOnMarkerClickListener { clickedMarker, mapView ->
            // Show a popup with the title and description
            showPopup(id)
            true // Return true to indicate the click was handled
        }
        mapView.overlays.add(marker)
    }

    private fun showPopup(id: String) {
        val popupView = layoutInflater.inflate(R.layout.item_aircraft, null)



        // Erstelle das PopupWindow
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        // Zeige das PopupWindow
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)

        // Interaktion mit den Elementen im Layout
        val btnClose = popupView.findViewById<Button>(R.id.btnClose)
        btnClose.setOnClickListener {
            popupWindow.dismiss() // Schließen des Popups
        }
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
