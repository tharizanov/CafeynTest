package com.example.cafeyntest.ui.fragments.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafeyntest.domains.network.ResponseItem
import com.example.cafeyntest.domains.ui.HomeRecyclerItem
import com.example.cafeyntest.events.ItemClickedEvent
import com.example.cafeyntest.events.VMEvent
import com.example.cafeyntest.network.ApiRequestRepository
import com.example.cafeyntest.network.RequestResult
import com.example.cafeyntest.storage.DatabaseManager
import com.example.cafeyntest.util.SingleLiveEvent
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

class HomeFragmentVM : ViewModel() {

    val event = SingleLiveEvent<VMEvent?>()
    val items = MutableLiveData<List<HomeRecyclerItem>?>()

    init {
        loadData()
    }

    fun onItemClick(item: HomeRecyclerItem) {
        event.value = ItemClickedEvent(item)
        Log.d(javaClass.simpleName, "Clicked id: ${item.id}")

        // Fallback due to Fragment subscription not working
        EventBus.getDefault().post(event.value)
    }

    private fun loadData() {
        viewModelScope.launch {
            loadStoredData()

            when (val result = ApiRequestRepository.requestItems()) {
                is RequestResult.Success ->
                    parseResponse(result.data)

                is RequestResult.Error ->
                    Log.e(javaClass.simpleName, result.throwable.message ?: "Unknown error")
            }
        }
    }

    private suspend fun loadStoredData() {
        ArrayList<HomeRecyclerItem>().run {
            for (item in DatabaseManager.getAllItems()) {
                add(HomeRecyclerItem(
                    item.id.toString(),
                    item.albumId.toString(),
                    item.title,
                    item.url,
                    item.thumbnailUrl
                ))
            }
            items.postValue(this)
        }
    }

    private suspend fun parseResponse(response: ArrayList<ResponseItem>) {
        ArrayList<HomeRecyclerItem>(response.size).run {
            for (item in response) {
                DatabaseManager.storeItem(item)
                add(HomeRecyclerItem(
                    item.id.toString(),
                    item.albumId.toString(),
                    item.title,
                    item.url,
                    item.thumbnailUrl
                ))
            }
            items.postValue(this)
        }
    }

}