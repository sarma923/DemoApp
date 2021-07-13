package com.example.demoapp.data.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.data.ProfileDatabase
import com.example.demoapp.data.model.Profile
import com.example.demoapp.data.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application : Application) : AndroidViewModel(application) {

    private val profileDao = ProfileDatabase.getDatabase(application).profileDao()
    private val profileRepository : ProfileRepository

    init {
        profileRepository = ProfileRepository(profileDao)
    }

    fun insertData(profile: Profile){
        viewModelScope.launch(Dispatchers.IO){
            profileRepository.insertData(profile)
        }
    }

    fun updateData(profile: Profile){
        viewModelScope.launch(Dispatchers.IO){
            profileRepository.updateData(profile)
        }
    }



    fun verifyDataFromUser(firstname : String,middlename : String,lastname : String,
    dob : String,address : String,education : String,city : String,state : String,
                           country : String,pincode : String,number : String) : Boolean{
        return if(TextUtils.isEmpty(firstname) ||
            TextUtils.isEmpty(middlename) ||
            TextUtils.isEmpty(lastname) ||
            TextUtils.isEmpty(dob) ||
            TextUtils.isEmpty(address) ||
            TextUtils.isEmpty(education) ||
            TextUtils.isEmpty(city) ||
            TextUtils.isEmpty(state) ||
            TextUtils.isEmpty(country) ||
            TextUtils.isEmpty(pincode)||
            TextUtils.isEmpty(number))
            false
        else !(firstname.isEmpty() ||
                middlename.isEmpty() ||
                lastname.isEmpty() ||
                dob.isEmpty() ||
                address.isEmpty() ||
                education.isEmpty() ||
                city.isEmpty() ||
                state.isEmpty() ||
                country.isEmpty() ||
                pincode.isEmpty()||
                number.isEmpty())
    }


}