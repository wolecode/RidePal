package com.ridehub360.ridepal.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridehub360.ridepal.R

class ReadingsFragment : Fragment() {

    companion object {
        fun newInstance() = ReadingsFragment()
    }

    private lateinit var viewModel: ReadingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.readings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReadingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}