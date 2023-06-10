package com.zenith.ecoscan.data.api

import com.zenith.ecoscan.data.api.response.Item
import com.zenith.ecoscan.data.api.response.ResponseData
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("predict")
    fun uploadPhoto(
        @Part file: MultipartBody.Part
    ) : Call<ResponseData>

}