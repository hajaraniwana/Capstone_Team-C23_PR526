package com.zenith.ecoscan.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zenith.ecoscan.data.api.ApiService
import com.zenith.ecoscan.data.api.response.ResponseData
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

    override fun uploadPhoto(file: File): LiveData<Result<ResponseData>> {
        val itemResponse = MutableLiveData<Result<ResponseData>>()
        itemResponse.value = Result.Loading
        val newFile = reduceFileImage(file)
        val fileReady = newFile.asRequestBody("image/jpg".toMediaType())
        val multipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "file",
            newFile.name,
            fileReady
        )
        apiService.uploadPhoto(multipart).enqueue(object :Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful) {
                    if (response.body()?.item != null) {
                        val item = response.body()?.item
                        item?.let {
                            val dataReady = DataEntity(
                                0,
                                item.averageEnergy.toString(),
                                item.dampakDisposal,
                                item.dampakProduksi,
                                item.name,
                                item.dampakKonsumsi,
                                item.image,
                                item.linkSumber
                            )
                            executorService.execute { dataDao.insertData(dataReady) }
                        }

                        itemResponse.value = Result.Success(response.body()!!)
                    } else {
                        itemResponse.value = Result.Error("Can't detect photo")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                itemResponse.value = Result.Error(t.message ?: "Unknown Error")
            }

        })
        return itemResponse
    }

    override fun getAllHistory(): LiveData<List<DataEntity>> = dataDao.getAllHistory()
}