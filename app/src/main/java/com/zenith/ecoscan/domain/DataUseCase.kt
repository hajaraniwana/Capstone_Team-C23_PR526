package com.zenith.ecoscan.domain

import androidx.lifecycle.LiveData
import com.zenith.ecoscan.data.api.response.CalculateResponse
import com.zenith.ecoscan.data.api.response.DataResponse
import com.zenith.ecoscan.data.api.response.FormResponse
import com.zenith.ecoscan.data.local.DataEntity
import com.zenith.ecoscan.utils.Result
import java.io.File

interface DataUseCase {
    fun uploadPhoto(file: File) : LiveData<Result<DataResponse>>
    fun getAllHistory() : LiveData<List<DataEntity>>

    fun getDevices(location: String): LiveData<Result<FormResponse>>

    fun calculateEnergy(location: String, device: String, device_type: String)
            : LiveData<Result<CalculateResponse>>
}