package com.zenith.ecoscan.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_data")
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val averageEnergy: String?,
    val dampakDisposal: String?,
    val dampakProduksi: String?,
    val name: String?,
    val dampakKonsumsi: String?,
    val image: String?,
    val linkSumber: String?
)