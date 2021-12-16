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

    override fun onBackPressed() {
        if (detailsFragment.isVisible)
            supportFragmentManager.beginTransaction().detach(detailsFragment).commit()
        else
            super.onBackPressed()
    }

    fun transitionToDetailsFragment(item: HomeRecyclerItem) {
        detailsFragment.arguments = Bundle().apply {
            putParcelable(DetailsFragment.ARG_KEY_ITEM, item)
        }

        if (supportFragmentManager.findFragmentByTag(DetailsFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, detailsFragment, DetailsFragment.TAG)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .attach(detailsFragment)
                .commit()
        }
    }

}