package com.ridehub360.ridepal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.ridehub360.ridepal.ui.OnBoardData
import com.ridehub360.ridepal.ui.OnBoardingAdapter
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity() {

    private var dotCount: Int = 0
    private lateinit var dotImages: ArrayList<ImageView>
    private lateinit var adapter: OnBoardingAdapter
    private var onBoardDataItems: ArrayList<OnBoardData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        loadData()
        setupViewPager()
        setupView()
    }

    private fun loadData() {
        val firstData = OnBoardData(R.drawable.ic_onboarding_img1, resources.getString(R.string.explore_your), resources.getString(R.string.surroundings), resources.getString(R.string.lorem_ipsum))
        val secondData = OnBoardData(R.drawable.ic_onboarding_img2, resources.getString(R.string.track_your), resources.getString(R.string.location), resources.getString(R.string.lorem_ipsum))
        val thirdData = OnBoardData(R.drawable.ic_onboarding_img3, resources.getString(R.string.live_health), resources.getString(R.string.readings), resources.getString(R.string.lorem_ipsum))
        onBoardDataItems.add(firstData)
        onBoardDataItems.add(secondData)
        onBoardDataItems.add(thirdData)
    }

    private fun setupViewPager() {
        adapter = OnBoardingAdapter(this, onBoardDataItems)
        view_pager.adapter = adapter
        view_pager.currentItem = 0
        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotCount) {
                    dotImages[i].setImageDrawable(ContextCompat.getDrawable(this@OnBoardingActivity, R.drawable.item_dot_non_selected))
                }
                dotImages[position].setImageDrawable(ContextCompat.getDrawable(this@OnBoardingActivity, R.drawable.item_dot_selected))
                if (position == 2) {
                    bt_start.visibility = View.VISIBLE
                    tv_skip.visibility = View.INVISIBLE
                } else {
                    bt_start.visibility = View.INVISIBLE
                    tv_skip.visibility = View.VISIBLE
                }
            }
        })
        setupPageCounter()
    }

    private fun setupPageCounter() {
        dotCount = adapter.count
        dotImages = ArrayList(dotCount)
        for (i in 0 until dotCount) {
            dotImages.add(i, ImageView(this))
            dotImages[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.item_dot_non_selected))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(6, 0, 6, 0)
            view_pager_counter_layout.addView(dotImages[i], params)
        }
        dotImages[0].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.item_dot_selected))
    }

    private fun setupView() {
        bt_start.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        tv_skip.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}