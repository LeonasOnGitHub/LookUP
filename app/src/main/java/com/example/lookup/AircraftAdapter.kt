package com.example.lookup

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lookup.models.FlightData

class AircraftAdapter(private val aircraftList: List<FlightData>) :
    RecyclerView.Adapter<AircraftAdapter.AircraftViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AircraftViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_aircraft_collection, parent, false)
        return AircraftViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AircraftViewHolder, position: Int) {
        val aircraft = aircraftList[position]
        holder.tvCallsign.text = "Callsign: ${aircraft.callSign}"
        holder.tvId.text = "ID: ${aircraft.id}"
        holder.tvOrigen.text = "Origen: ${aircraft.origin}"
        holder.tvCategory.text = "Category: ${aircraft.category}"
        holder.tvVelocity.text = "Velocity: ${aircraft.velocity} m/s"
        //holder.tvLat.text = aircraft.latitude.toString()
        //holder.tvLong.text = aircraft.longitude.toString()
    }

    override fun getItemCount(): Int = aircraftList.size

    class AircraftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCallsign: TextView = itemView.findViewById(R.id.tvCallSign)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvOrigen: TextView = itemView.findViewById(R.id.tvOrigen)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val tvVelocity: TextView = itemView.findViewById(R.id.tvVelocity)
        //val tvLat: TextView = itemView.findViewById(R.id.tv)
        //val tvLong: TextView = itemView.findViewById(R.id.tvTitle)
    }
}
