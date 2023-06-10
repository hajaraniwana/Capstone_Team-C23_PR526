package com.zenith.ecoscan.domain

import androidx.lifecycle.LiveData
import com.zenith.ecoscan.data.api.response.ResponseData
import com.zenith.ecoscan.data.local.DataEntity
import com.zenith.ecoscan.utils.Result
import java.io.File

interface DataUseCase {
    fun uploadPhoto(file: File) : LiveData<Result<ResponseData>>
    fun getAllHistory() : LiveData<List<DataEntity>>
}