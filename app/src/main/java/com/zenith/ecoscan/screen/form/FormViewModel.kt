package com.zenith.ecoscan.screen.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zenith.ecoscan.data.api.response.CalculateResponse
import com.zenith.ecoscan.data.api.response.FormResponse
import com.zenith.ecoscan.domain.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.zenith.ecoscan.utils.Result

@HiltViewModel
class FormViewModel @Inject constructor(private val useCase: DataUseCase): ViewModel() {
    fun getDevices(location: String): LiveData<Result<FormResponse>> {
        return useCase.getDevices(location)
    }

    fun calculateEnergy(location: String, device: String, device_type: String)
        : LiveData<Result<CalculateResponse>> {
        return useCase.calculateEnergy(location, device, device_type)
    }
}