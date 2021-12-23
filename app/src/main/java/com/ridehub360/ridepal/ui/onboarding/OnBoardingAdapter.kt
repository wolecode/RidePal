package com.ridehub360.ridepal.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class OnBoardingAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle().apply {
            putInt("POSITION", position)
        }
        return OnBoardingPagerFragment().apply {
            arguments = bundle
        }
    }

}









/*
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
)*/
