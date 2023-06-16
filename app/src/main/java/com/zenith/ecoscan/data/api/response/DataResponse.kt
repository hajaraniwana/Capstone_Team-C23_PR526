package com.zenith.ecoscan.data.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DataResponse(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("item_info")
	val itemInfo: ItemInfo? = null,

	@field:SerializedName("time")
	val time: String? = null
) : Parcelable