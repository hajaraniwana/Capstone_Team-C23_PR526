package com.zenith.ecoscan.screen.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zenith.ecoscan.data.local.DataEntity
import com.zenith.ecoscan.domain.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val useCase: DataUseCase) : ViewModel() {
    fun getAllHistory() : LiveData<List<DataEntity>> {
        return useCase.getAllHistory()
    }
}