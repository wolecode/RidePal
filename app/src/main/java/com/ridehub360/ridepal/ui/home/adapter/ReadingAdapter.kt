package com.ridehub360.ridepal.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ridehub360.ridepal.databinding.ReadingsAdapterBinding
import com.ridehub360.ridepal.model.ReadingsModel

class ReadingAdapter:
    RecyclerView.Adapter<ReadingAdapter.ReadingsViewHolder>() {
    var list = listOf<ReadingsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingAdapter.ReadingsViewHolder {
        return ReadingsViewHolder(
            ReadingsAdapterBinding
                .inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ReadingAdapter.ReadingsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ReadingsViewHolder(private val binding: ReadingsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(model: ReadingsModel) {

            }
        }
}