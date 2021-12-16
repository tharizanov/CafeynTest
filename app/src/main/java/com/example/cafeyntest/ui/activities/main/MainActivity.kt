package com.example.cafeyntest.ui.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeyntest.R
import com.example.cafeyntest.domains.ui.HomeRecyclerItem
import com.example.cafeyntest.ui.fragments.details.DetailsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var detailsFragment: DetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        detailsFragment = DetailsFragment()
    }

    fun transitionToDetailsFragment(item: HomeRecyclerItem) {
        detailsFragment.arguments = Bundle().apply {
            putParcelable(DetailsFragment.ARG_KEY_ITEM, item)
        }

        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_fragment_container, detailsFragment, DetailsFragment.TAG)
            .commit()
    }

}