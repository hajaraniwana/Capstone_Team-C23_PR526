package com.zenith.ecoscan.screen.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zenith.ecoscan.data.api.response.DataResponse
import com.zenith.ecoscan.domain.DataUseCase
import com.zenith.ecoscan.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(private val dataUseCase: DataUseCase) : ViewModel() {
    fun uploadPhoto(file: File) : LiveData<Result<DataResponse>> {
        return dataUseCase.uploadPhoto(file)
    }
}