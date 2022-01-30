package com.ridehub360.ridepal.di

import androidx.lifecycle.ViewModelProvider
import com.ridehub360.ridepal.ui.home.HomeFragment
import com.ridehub360.ridepal.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class ViewModelProviders {

    @Provides
    fun homeViewModel(home: HomeFragment) = ViewModelProvider(home)[HomeViewModel::class.java]

    @Provides
    fun homeFragment() = HomeFragment()
}