package com.zenith.ecoscan.data.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseData(
    @SerializedName("item")
    val item: Item,
    @SerializedName("result")
    val result: String
)

@Parcelize
data class Item(

    @field:SerializedName("Average Energy")
    val averageEnergy: Double? = null,

    @field:SerializedName("Dampak Disposal")
    val dampakDisposal: String? = null,

    @field:SerializedName("Dampak Produksi")
    val dampakProduksi: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("Dampak Konsumsi")
    val dampakKonsumsi: String? = null,

    @field:SerializedName("Image")
    val image: String? = null,

    @field:SerializedName("Link Sumber")
    val linkSumber: String? = null
) : Parcelable
