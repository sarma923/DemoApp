package com.example.demoapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.demoapp.data.model.Profile

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(profile: Profile)

    @Update
    suspend fun updateData(profile: Profile)
}