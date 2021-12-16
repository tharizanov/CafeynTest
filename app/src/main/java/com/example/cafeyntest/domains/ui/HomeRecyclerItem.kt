package com.example.cafeyntest.domains.ui

import android.os.Parcel
import android.os.Parcelable

data class HomeRecyclerItem(
    val id: String?,
    val albumId: String?,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents() = hashCode()

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.run {
            writeString(id)
            writeString(albumId)
            writeString(title)
            writeString(url)
            writeString(thumbnailUrl)
        }
    }

    companion object CREATOR : Parcelable.Creator<HomeRecyclerItem> {
        override fun createFromParcel(parcel: Parcel): HomeRecyclerItem {
            return HomeRecyclerItem(parcel)
        }

        override fun newArray(size: Int): Array<HomeRecyclerItem?> {
            return arrayOfNulls(size)
        }
    }

}
