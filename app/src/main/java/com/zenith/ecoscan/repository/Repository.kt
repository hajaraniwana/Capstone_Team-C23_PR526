package com.zenith.ecoscan.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zenith.ecoscan.data.api.ApiService
import com.zenith.ecoscan.data.api.response.CalculateResponse
import com.zenith.ecoscan.data.api.response.DataResponse
import com.zenith.ecoscan.data.api.response.FormResponse
import com.zenith.ecoscan.data.local.DataDao
import com.zenith.ecoscan.data.local.DataEntity
import com.zenith.ecoscan.domain.IDataRepository
import com.zenith.ecoscan.utils.Result
import com.zenith.ecoscan.utils.reduceFileImage
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService, private val dataDao: DataDao) :
    IDataRepository {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    override fun uploadPhoto(file: File): LiveData<Result<DataResponse>> {
        val itemResponse = MutableLiveData<Result<DataResponse>>()
        itemResponse.value = Result.Loading
        val newFile = reduceFileImage(file)
        val fileReady = newFile.asRequestBody("image/jpg".toMediaType())
        val multipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "file",
            newFile.name,
            fileReady
        )
        apiService.uploadPhoto(multipart).enqueue(object :Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.itemInfo != null) {
                        val item = response.body()?.itemInfo
                        item?.let {
                            val dataReady = DataEntity(
                                0,
                                item.lokasi,
                                item.averageEnergy.toString(),
                                item.dampakDisposal,
                                item.dampakProduksi,
                                item.name,
                                item.dampakKonsumsi,
                                item.image,
                                item.sumber,
                                item.recommendations,
                                response.body()?.time
                            )
                            executorService.execute { dataDao.insertData(dataReady) }
                        }

                        itemResponse.value = Result.Success(response.body()!!)
                    } else {
                        itemResponse.value = Result.Error("Can't detect photo")
                    }
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                itemResponse.value = Result.Error(t.message ?: "Unknown Error")
            }

        })
        return itemResponse
    }

    override fun getAllHistory(): LiveData<List<DataEntity>> = dataDao.getAllHistory()
    override fun getDevicesData(location: String): LiveData<Result<FormResponse>> {
        val formResponse = MutableLiveData<Result<FormResponse>>()

        formResponse.value = Result.Loading

        apiService.getDevices(location).enqueue(object : Callback<FormResponse>{
            override fun onResponse(call: Call<FormResponse>, response: Response<FormResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.devices != null) {
                        formResponse.value = Result.Success(response.body()!!)
                    } else {
                        formResponse.value = Result.Error("No Data")
                    }
                }
            }

            override fun onFailure(call: Call<FormResponse>, t: Throwable) {
                formResponse.value = Result.Error(t.message ?: "Unknown Error")
            }

        })
        return formResponse
    }

    override fun calculateEnergy(
        location: String,
        device: String,
        device_type: String
    ): LiveData<Result<CalculateResponse>> {
        val energyResponse = MutableLiveData<Result<CalculateResponse>>()
        energyResponse.value = Result.Loading

        val requestBody = mapOf(
            "location" to location,
            "device" to device,
            "device_type" to device_type
        )

        apiService.calculateEnergy(requestBody).enqueue(object : Callback<CalculateResponse>{
            override fun onResponse(
                call: Call<CalculateResponse>,
                response: Response<CalculateResponse>
            ) {
                if (response.isSuccessful) {
                    energyResponse.value = Result.Success(response.body()!!)
                }
            }

            override fun onFailure(call: Call<CalculateResponse>, t: Throwable) {
                energyResponse.value = Result.Error(t.message ?: "Unknown Error")
            }

        })
        return energyResponse
    }
}