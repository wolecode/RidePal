package com.ridehub360.ridepal.ui.home.adapter

import android.graphics.Point
import android.graphics.Rect
import android.transition.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.core.view.doOnLayout
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.RecyclerView
import com.ridehub360.ridepal.R
import com.ridehub360.ridepal.databinding.RecentLocationsAdapterBinding
import com.ridehub360.ridepal.model.DestinationModel

class RecentLocationAdapter(val recycler: () -> RecyclerView) :
    RecyclerView.Adapter<RecentLocationAdapter.LocationViewHolder>() {
    var list = listOf<DestinationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            RecentLocationsAdapterBinding
                .inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class LocationViewHolder(private val binding: RecentLocationsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.apply {

                doOnPreDraw{
                    val rect = Rect()
                    val points = Point()
                    val metrics = resources.displayMetrics
                    val widthPixel = metrics.widthPixels
                    Log.d("SCREEN PIXEL", "${metrics.widthPixels}," +
                            " ${metrics.heightPixels}, ${metrics.densityDpi}")
                    getGlobalVisibleRect(rect, points)
                    val x1 = (points.x).toDouble()
                    val x2 = x1 + rect.width()


                    scaleY = if (x1 >= 0.9 * widthPixel) {
                        0.5f
                    } else if( x1 >= 0.8 * widthPixel && x1 < 0.9 * widthPixel) {
                        0.6f
                    } else if (x1 >= 0.7 * widthPixel && x1 < 0.8 * widthPixel) {
                        0.7f
                    } else if (x1 >= 0.6 * widthPixel && x1 < 0.7 * widthPixel) {
                        0.8f
                    } else if(x1 >= 0.5 * widthPixel && x1 < 0.6 * widthPixel) {
                        0.8f
                    } else {
                        1.0f
                    }

                }

                recycler().addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(
                        recyclerView: RecyclerView,
                        newState: Int
                    ) {
                        super.onScrollStateChanged(recyclerView, newState)
                    }

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                            val rect = Rect()
                            val points = Point()
                            val metrics = resources.displayMetrics
                            val widthPixel = metrics.widthPixels
                            getGlobalVisibleRect(rect, points)
                            val x1 = (points.x).toDouble()
                            val x2 = x1 + rect.width()

                            scaleY = when {
                                x1 in (0.9 * widthPixel ..widthPixel.toDouble())
                                        || x2 in (0.0 ..0.1 * widthPixel) -> {
                                    0.5f
                                }
                                x1 in (0.8 * widthPixel ..0.9 *widthPixel)
                                        || x2 in (0.1 * widthPixel ..0.2 * widthPixel) -> {
                                    0.6f
                                }
                                x1 in (0.7 * widthPixel ..0.8 *widthPixel)
                                        || x2 in (0.2 * widthPixel ..0.3 * widthPixel) -> {
                                    0.7f
                                }
                                x1 in (0.6 * widthPixel ..0.7 *widthPixel)
                                        || x2 in (0.3 * widthPixel ..0.4 * widthPixel) -> {
                                    0.8f
                                }
                                x1 in (0.5 * widthPixel ..0.6 *widthPixel)
                                        || x2 in (0.4 * widthPixel ..0.5 * widthPixel) -> {
                                    1.0f
                                }
                                else ->  1.0f
                            }
                        }

                })
            }


        }

        fun bind(model: DestinationModel) {
            binding.locationName.text = model.location.name
            /*binding.distance.text = model.distance
            binding.duration.text = model.duration*/
        }

    }

}