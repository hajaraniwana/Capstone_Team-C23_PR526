package com.zenith.ecoscan.domain

import androidx.lifecycle.LiveData
import com.zenith.ecoscan.data.api.response.Item
import com.zenith.ecoscan.data.api.response.ResponseData
import com.zenith.ecoscan.data.local.DataEntity
import com.zenith.ecoscan.utils.Result
import java.io.File
import javax.inject.Inject

class DataInteractor @Inject constructor(private val repository: IDataRepository) : DataUseCase {

    override fun uploadPhoto(file: File): LiveData<Result<ResponseData>> {
        return repository.uploadPhoto(file)
    }

    override fun getAllHistory(): LiveData<List<DataEntity>> {
        return repository.getAllHistory()
    }

}