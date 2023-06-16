package com.zenith.ecoscan.domain

import androidx.lifecycle.LiveData
import com.zenith.ecoscan.data.api.response.CalculateResponse
import com.zenith.ecoscan.data.api.response.DataResponse
import com.zenith.ecoscan.data.api.response.FormResponse
import com.zenith.ecoscan.data.local.DataEntity
import com.zenith.ecoscan.utils.Result
import java.io.File
import javax.inject.Inject

class DataInteractor @Inject constructor(private val repository: IDataRepository) : DataUseCase {

    override fun uploadPhoto(file: File): LiveData<Result<DataResponse>> {
        return repository.uploadPhoto(file)
    }

    override fun getAllHistory(): LiveData<List<DataEntity>> {
        return repository.getAllHistory()
    }

    override fun getDevices(location: String): LiveData<Result<FormResponse>> {
        return repository.getDevicesData(location)
    }

    override fun calculateEnergy(
        location: String,
        device: String,
        device_type: String
    ): LiveData<Result<CalculateResponse>> {
        return repository.calculateEnergy(location, device, device_type)
    }

}