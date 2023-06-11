package com.zenith.ecoscan.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {

    @Query("SELECT * FROM history_data")
    fun getAllHistory() : LiveData<List<DataEntity>>

    @Insert
    fun insertData(dataEntity: DataEntity)
}