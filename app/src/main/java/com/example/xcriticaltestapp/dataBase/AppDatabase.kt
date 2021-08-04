package com.example.xcriticaltestapp.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xcriticaltestapp.dataBase.DAO.ProjectDAO
import com.example.xcriticaltestapp.dataBase.entities.ProjectEntity

@Database(entities = [ProjectEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val projectDao: ProjectDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDb"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}