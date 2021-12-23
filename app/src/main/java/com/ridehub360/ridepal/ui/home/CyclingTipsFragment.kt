package com.ridehub360.ridepal.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridehub360.ridepal.R

class CyclingTipsFragment : Fragment() {

    companion object {
        fun newInstance() = CyclingTipsFragment()
    }

    private lateinit var viewModel: CyclingTipsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cycling_tips_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CyclingTipsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}