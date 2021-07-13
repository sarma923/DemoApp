package com.example.demoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoapp.data.model.Profile


@Database(entities = [Profile::class],version = 1,exportSchema = true)
abstract class ProfileDatabase() : RoomDatabase() {

    abstract fun profileDao() : ProfileDao
    companion object {

        @Volatile
        private var INSTANCE: ProfileDatabase? = null

        fun getDatabase(context: Context) : ProfileDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProfileDatabase::class.java,
                    "profile_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}