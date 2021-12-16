package com.example.cafeyntest.ui.fragments.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cafeyntest.R
import com.example.cafeyntest.domains.ui.HomeRecyclerItem

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel: DetailsFragmentVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getParcelable<HomeRecyclerItem>(ARG_KEY_ITEM)?.let {
            viewModel.item.value = it
        }
    }

    companion object {
        const val ARG_KEY_ITEM = "argument_item"
        const val TAG = "DetailsFragment"
    }

}