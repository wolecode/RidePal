package com.ridehub360.ridepal.ui.home

import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridehub360.ridepal.R
import com.ridehub360.ridepal.databinding.FragmentHomeBinding
import com.ridehub360.ridepal.ui.home.adapter.RecentLocationAdapter
import com.ridehub360.ridepal.ui.home.adapter.RecentReadingsAdapter

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recentLocationAdapter: RecentLocationAdapter
    private lateinit var recentReadingsAdapter: RecentReadingsAdapter
    private val rect = Rect()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        minHeightForScrollViewContent()
        setUpView()
        observeData()
        return binding.root
    }

   private fun minHeightForScrollViewContent() {
       binding.linearLayout.apply {
           doOnPreDraw {
               val rect = Rect()
               getGlobalVisibleRect(rect)
               val typedValue = TypedValue()
               requireContext().theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)
               val actionBarSize = TypedValue.complexToDimensionPixelSize(typedValue.data, resources.displayMetrics)
               minimumHeight = rect.height() + actionBarSize
           }
       }
   }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun setUpView() {
        val layoutManager1 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val layoutManager2 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val decorator =
            DividerItemDecoration(requireContext(), layoutManager1.orientation).apply {
                setDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.divider
                    )!!
                )
            }

        with(binding) {
            username.text =
                Html.fromHtml(getString(R.string.welcome1, "Kolawole"), FROM_HTML_MODE_LEGACY)
            locationRecyclerView.apply {
                recentLocationAdapter = RecentLocationAdapter { this }
                layoutManager = layoutManager1
                adapter = recentLocationAdapter
                addItemDecoration(decorator)
            }
            readingsRecyclerView.apply {
                recentReadingsAdapter = RecentReadingsAdapter { this }
                layoutManager = layoutManager2
                adapter = recentReadingsAdapter
                addItemDecoration(decorator)
            }
        }
    }

    private fun observeData() {
        homeViewModel._liveData.observe(viewLifecycleOwner) {
            recentLocationAdapter.list = it
            println(it.size)
            recentLocationAdapter.notifyDataSetChanged()
        }

        homeViewModel._liveData1.observe(viewLifecycleOwner) {
            recentReadingsAdapter.list = it
            println(it.size)
            recentReadingsAdapter.notifyDataSetChanged()
        }
    }
}
