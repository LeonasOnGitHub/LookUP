package com.example.lookup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lookup.databinding.ActivityMapBinding
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding initialisieren
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // osmdroid-Konfiguration
        Configuration.getInstance().load(this, getSharedPreferences("osmdroid", MODE_PRIVATE))

        // MapView initialisieren
        mapView = binding.mapView
        mapView.setMultiTouchControls(true)

        // Karte auf einen Standardort zentrieren (z. B. Berlin)
        val mapController = mapView.controller
        mapController.setZoom(10.0)
        mapController.setCenter(org.osmdroid.util.GeoPoint(52.5200, 13.4050))

        // Beispiel-Daten: Marker hinzufügen
        addAircraftMarker(52.5200, 13.4050, "Boeing 747")
        addAircraftMarker(48.8566, 2.3522, "Airbus A320")

        // Button-Klick-Listener
        binding.btnRefresh.setOnClickListener {
            Toast.makeText(this, "Refreshing aircraft data...", Toast.LENGTH_SHORT).show()
            // Hier kannst du neue Daten laden
        }
    }

    private fun addAircraftMarker(lat: Double, lon: Double, title: String) {
        val marker = Marker(mapView)
        marker.position = org.osmdroid.util.GeoPoint(lat, lon)
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = title
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
