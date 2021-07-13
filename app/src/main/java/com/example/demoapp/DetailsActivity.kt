package com.example.demoapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.data.model.Profile
import com.example.demoapp.data.viewmodel.ProfileViewModel
import com.example.demoapp.databinding.ActivityDetailsBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class DetailsActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val mProfileViewModel : ProfileViewModel by viewModels()


    private var date : String ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityDetailsBinding.inflate(layoutInflater)
         val view = binding.root
         setContentView(view)

        val education = resources.getStringArray(R.array.education)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item,education)
        binding.education.setAdapter(arrayAdapter)

        binding.datePicker.setOnClickListener {
            getDate()
        }
        binding.submit.setOnClickListener {
             if(date==null)
             {
                 Toast.makeText(this,"Please fill all the details!",Toast.LENGTH_SHORT).show()
             }
             else
             {
                 insertIntoDatabase(date!!)
                 Toast.makeText(this,"Profile Updated",Toast.LENGTH_SHORT).show()
                 val intent = Intent(this,MainActivity::class.java)
                 startActivity(intent)
                 finish()
             }


         }
    }

    private fun insertIntoDatabase(date : String)
    {
         binding.run {
             val first = firstName.text.toString()
             val middle = middleName.text.toString()
             val last = lastName.text.toString()
             val address = address.text.toString()
             val education = education.text.toString()
             val city = city.text.toString()
             val state = state.text.toString()
             val country = country.text.toString()
             val pin = pinCode.text.toString()
             val phone = mobileNumber.text.toString()


             Log.d("DOB",date)
             val validation = mProfileViewModel.verifyDataFromUser(first,middle,last,date,address,education,
             city,state,country,pin,phone)

             if(validation)
             {
                 val newData = Profile(0,first,middle,last,date,address,
                     education,city,state,country,pin,phone)

                 mProfileViewModel.insertData(newData)
             }
             else
             {
                 Toast.makeText(this@DetailsActivity,"Please fill all the details!",
                     Toast.LENGTH_SHORT).show()
             }





         }
    }

    private fun getDate(){

             val datePicker = MaterialDatePicker.Builder.datePicker().build()
             datePicker.show(supportFragmentManager,"DatePicker")
             datePicker.addOnPositiveButtonClickListener {
                 val dateFormatter = SimpleDateFormat("dd-mm-yyyy")
                 date = dateFormatter.format(Date(it))
                 binding.dateOfBirth.setText(date.toString())
                 Log.d("datePicker",date!!)


        }

    }


}