package com.ridehub360.ridepal.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ridehub360.ridepal.R
import com.ridehub360.ridepal.databinding.ReadingsFragmentBinding
import com.ridehub360.ridepal.ui.home.adapter.ReadingAdapter

class ReadingsFragment : Fragment() {

    private lateinit var binding: ReadingsFragmentBinding
    private val readingsViewModel: ReadingsViewModel by viewModels()
    lateinit var readingAdapter: ReadingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ReadingsFragmentBinding.inflate(inflater)
        readingAdapter = ReadingAdapter()
        setUpView()
        observeData()
        return binding.root
    }

    private fun setUpView() {
        val layout = LinearLayoutManager(requireContext())
        val decorator = DividerItemDecoration(requireContext(), layout.orientation).apply {
            setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.horizontal_divider)!!)
        }
        binding.readingRecyclerView.apply {
            layoutManager = layout
            adapter = readingAdapter
            addItemDecoration(decorator)
        }
    }

    private fun observeData() {
        readingsViewModel._liveData1.observe(viewLifecycleOwner) {
            readingAdapter.list = it
            readingAdapter.notifyDataSetChanged()
        }
    }
}