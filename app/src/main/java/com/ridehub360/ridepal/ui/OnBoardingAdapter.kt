package com.ridehub360.ridepal.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.ridehub360.ridepal.R

class OnBoardingAdapter(private val context: Context, private val onBoardDataItems: ArrayList<OnBoardData>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_view_onboard, container, false)
        val item = onBoardDataItems[position]
        itemView.findViewById<ImageView>(R.id.iv1).setImageResource(item.imageId)
        itemView.findViewById<TextView>(R.id.tv1).text = item.headerText1
        itemView.findViewById<TextView>(R.id.tv2).text = item.headerText2
        itemView.findViewById<TextView>(R.id.tv3).text = item.descriptionText
        container.addView(itemView)
        return itemView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = onBoardDataItems.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}

data class OnBoardData(
    val imageId: Int,
    val headerText1: String,
    val headerText2: String,
    val descriptionText: String
)