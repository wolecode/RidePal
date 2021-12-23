package com.ridehub360.ridepal.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ridehub360.ridepal.R
import com.ridehub360.ridepal.databinding.FragmentOnboardingPagerBinding


class OnBoardingPagerFragment : Fragment() {
    lateinit var binding: FragmentOnboardingPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingPagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpView()
    }

    private fun setUpView() {
        val bundle = arguments
        val position = bundle!!.getInt("POSITION")

        when (position) {
            0 -> {
                with(binding) {
                    pagerImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_onboarding_img1
                        )
                    )
                    header1.text = "Explore your"
                    header2.text = "Surroundings"
                }
            }
            1 -> {
                with(binding) {
                    pagerImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_onboarding_img2
                        )
                    )
                    header1.text = "Track your"
                    header2.text = "Location"
                }
            }
            else -> {
                with(binding) {
                    pagerImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_onboarding_img3
                        )
                    )
                    header1.text = "Live Health"
                    header2.text = "Readings"
                }
            }
        }
    }

}
