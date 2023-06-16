package com.zenith.ecoscan.data.api

import com.zenith.ecoscan.data.api.response.CalculateResponse
import com.zenith.ecoscan.data.api.response.DataResponse
import com.zenith.ecoscan.data.api.response.FormResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @Multipart
    @POST("predict")
    fun uploadPhoto(
        @Part file: MultipartBody.Part
    ) : Call<DataResponse>

    @GET("devices")
    fun getDevices(
        @Query("location") location: String
    ) : Call<FormResponse>

    @POST("form")
    fun calculateEnergy(@Body requestBody: Map<String, String>
    ): Call<CalculateResponse>
}