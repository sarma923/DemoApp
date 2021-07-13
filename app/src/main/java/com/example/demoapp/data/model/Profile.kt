package com.example.demoapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "profile_table")
@Parcelize
data class Profile(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var firstName : String,
    var middleName : String,
    var lastName : String,
    var dob : String,
    var address : String,
    var education : String,
    var city : String,
    var state : String,
    var country : String,
    var pinCode : String,
    var phoneNumber : String

) : Parcelable
