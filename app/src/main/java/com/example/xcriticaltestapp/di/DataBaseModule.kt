package com.example.xcriticaltestapp.di

import android.content.Context
import com.example.xcriticaltestapp.dataBase.AppDatabase
import com.example.xcriticaltestapp.dataBase.DAO.ProjectDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideProjectDao(appDatabase: AppDatabase): ProjectDAO {
        return appDatabase.projectDao
    }
}