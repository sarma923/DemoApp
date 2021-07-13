package com.example.demoapp.data.repository

import com.example.demoapp.data.ProfileDao
import com.example.demoapp.data.model.Profile

class ProfileRepository(private val profileDao: ProfileDao) {

    suspend fun insertData(profile : Profile)
    {
        profileDao.insertData(profile)
    }

    suspend fun updateData(profile: Profile)
    {
        profileDao.updateData(profile)
    }
}