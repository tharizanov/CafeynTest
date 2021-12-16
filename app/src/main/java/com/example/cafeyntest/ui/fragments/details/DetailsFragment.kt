package com.example.cafeyntest.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cafeyntest.databinding.FragmentDetailsBinding
import com.example.cafeyntest.domains.ui.HomeRecyclerItem

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            arguments?.getParcelable<HomeRecyclerItem>(ARG_KEY_ITEM)?.let {
                item = it
            }
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        const val ARG_KEY_ITEM = "argument_item"
        const val TAG = "DetailsFragment"
    }

}