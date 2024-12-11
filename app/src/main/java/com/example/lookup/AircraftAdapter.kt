package com.example.lookup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lookup.models.Aircraft

class AircraftAdapter(private val aircraftList: List<Aircraft>) :
    RecyclerView.Adapter<AircraftAdapter.AircraftViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AircraftViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_aircraft, parent, false)
        return AircraftViewHolder(view)
    }

    override fun onBindViewHolder(holder: AircraftViewHolder, position: Int) {
        val aircraft = aircraftList[position]
        holder.tvModel.text = aircraft.model
        holder.tvTimestamp.text = aircraft.timestamp.toString()
    }

    override fun getItemCount(): Int = aircraftList.size

    class AircraftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvModel: TextView = itemView.findViewById(R.id.tvTitle)
        val tvTimestamp: TextView = itemView.findViewById(R.id.tvTitle) //#TODO
    }
}
