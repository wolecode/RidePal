package com.ridehub360.ridepal.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.ridehub360.ridepal.R
import com.ridehub360.ridepal.databinding.ActivityOnBoardingBinding
import com.ridehub360.ridepal.ui.login.SignInActivity

//import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    lateinit var adapters: OnBoardingAdapter
    private val counter = mutableListOf<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapters = OnBoardingAdapter(this)
        setUpView()
    }

    private fun setUpView() {
        with(binding) {
            viewPager.run {
                adapter = adapters
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        for (i in 0 until counter.size) {
                            counter[i].setImageDrawable(
                                ContextCompat.getDrawable(
                                    this@OnBoardingActivity,
                                    R.drawable.item_dot_non_selected
                                )
                            )
                        }
                        counter[position].setImageDrawable(
                            ContextCompat.getDrawable(
                                this@OnBoardingActivity,
                                R.drawable.item_dot_selected
                            )
                        )
                        if (position == 2) {
                            binding.tvSkip.visibility = View.GONE
                            binding.btStart.visibility = View.VISIBLE
                        } else {
                            binding.tvSkip.visibility = View.VISIBLE
                            binding.btStart.visibility = View.GONE
                        }
                    }
                })
            }
            tvSkip.setOnClickListener {
                startActivity(Intent(this@OnBoardingActivity, SignInActivity::class.java))
                finish()
            }
            btStart.setOnClickListener {
                startActivity(Intent(this@OnBoardingActivity, SignInActivity::class.java))
                finish()
            }
        }

        setUpCounter()
    }


    private fun setUpCounter() {

        for (i in 0 until adapters.itemCount) {
            counter.add(i, ImageView(this))
            counter[i].setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.item_dot_non_selected
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.customMargin(i)
            binding.counterLayout.addView(counter[i])
        }
        counter[0].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.item_dot_selected))
    }

    private fun LinearLayout.LayoutParams.customMargin(counterPosition: Int) {
        if (counterPosition == 0) {
            this.setMargins(10, 0, 0, 0)

        } else {
            this.setMargins(10, 0, 0, 0)
        }
    }
}
