package com.example.cafeyntest.ui.fragments.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cafeyntest.domains.ui.HomeRecyclerItem

class DetailsFragmentVM : ViewModel() {

    val item = MutableLiveData<HomeRecyclerItem>()

}