package com.example.cafeyntest.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeyntest.R
import com.example.cafeyntest.databinding.FragmentHomeBinding
import com.example.cafeyntest.events.ItemClickedEvent
import com.example.cafeyntest.ui.activities.main.MainActivity
import com.example.cafeyntest.ui.adapters.HomeRecyclerAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeFragmentVM by viewModels()
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@HomeFragment
            vm = viewModel
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.homeRecycler?.run {
            layoutManager = GridLayoutManager(view.context, 3, RecyclerView.HORIZONTAL, false)
            adapter = HomeRecyclerAdapter().apply {
                viewModel.items.value?.let { setItems(it) }
            }
        }

        viewModel.event.observe(this, { event ->
            if (event is ItemClickedEvent)
                (activity as? MainActivity)?.transitionToDetailsFragment(event.item)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}