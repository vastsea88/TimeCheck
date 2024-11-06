package com.hus.timecheck.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
class DaoModule {

    @Provides
    @ViewModelScoped
    fun providesCheckTimeResultDao(@ApplicationContext appContext: Context): CheckTimeResultDao =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration().build().checkTimeResultDao()

}