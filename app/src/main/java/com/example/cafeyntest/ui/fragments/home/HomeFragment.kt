package com.example.cafeyntest.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cafeyntest.databinding.FragmentHomeBinding
import com.example.cafeyntest.events.ItemClickedEvent
import com.example.cafeyntest.ui.activities.main.MainActivity
import com.example.cafeyntest.ui.adapters.HomeRecyclerAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HomeFragment : Fragment() {

    private val viewModel: HomeFragmentVM by viewModels()

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.homeRecycler?.run {
            layoutManager = GridLayoutManager(context, 3)
            adapter = HomeRecyclerAdapter().apply { setItems(viewModel.items.value) }
        }

        // NOT WORKING (using EventBus instead)
//        viewModel.event.observe(this, {
//            it?.let { event ->
//                if (event is ItemClickedEvent)
//                    onItemClickedEvent(event)
//            }
//        })
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onItemClickedEvent(event: ItemClickedEvent) {
        Log.d(javaClass.simpleName, "onItemClickedEvent")
        (activity as? MainActivity)?.transitionToDetailsFragment(event.item)
    }

}