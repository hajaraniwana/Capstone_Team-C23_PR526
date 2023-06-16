package com.zenith.ecoscan.data.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ItemInfo(

	@field:SerializedName("Lokasi")
	val lokasi: String? = null,

	@field:SerializedName("Average Energy")
	val averageEnergy: Double? = null,

	@field:SerializedName("Dampak Disposal")
	val dampakDisposal: String? = null,

	@field:SerializedName("Sumber")
	val sumber: String? = null,

	@field:SerializedName("Dampak Produksi")
	val dampakProduksi: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("Dampak Konsumsi")
	val dampakKonsumsi: String? = null,

	@field:SerializedName("Image")
	val image: String? = null,

	@field:SerializedName("recommendations")
	val recommendations: String? = null
) : Parcelable