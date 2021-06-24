package com.machinetask.modals

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PlacesModal() : Parcelable {
    @SerializedName("id")
    var id: Long? = 0

    @SerializedName("author")
    var author: String? = ""

    @SerializedName("width")
    var width: String? = ""

    @SerializedName("height")
    var height: String? = ""

    @SerializedName("url")
    var url: String? = ""

    @SerializedName("download_url")
    var downloadUrl: String? = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Long::class.java.classLoader) as? Long
        author = parcel.readString()
        width = parcel.readString()
        height = parcel.readString()
        url = parcel.readString()
        downloadUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(author)
        parcel.writeString(width)
        parcel.writeString(height)
        parcel.writeString(url)
        parcel.writeString(downloadUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlacesModal> {
        override fun createFromParcel(parcel: Parcel): PlacesModal {
            return PlacesModal(parcel)
        }

        override fun newArray(size: Int): Array<PlacesModal?> {
            return arrayOfNulls(size)
        }
    }

}