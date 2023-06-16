package com.zenith.ecoscan.data.api.response

import com.google.gson.annotations.SerializedName

data class FormResponse(

	@field:SerializedName("devices")
	val devices: List<DevicesItem?>? = null
)

data class DevicesItem(

	@field:SerializedName("detailed_type")
	val detailedType: String? = null,

	@field:SerializedName("device")
	val device: String? = null
)
