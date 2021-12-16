package com.example.cafeyntest.events

import com.example.cafeyntest.domains.ui.HomeRecyclerItem

sealed class VMEvent

class ItemClickedEvent(val item: HomeRecyclerItem) : VMEvent()