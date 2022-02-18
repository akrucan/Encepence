package com.github.akrucan.encepence.di

import android.app.Application
import androidx.room.Room
import com.github.akrucan.encepence.data.EncepenceDatabase
import com.github.akrucan.encepence.data.EncepenceRepositoryImpl
import com.github.akrucan.encepence.domain.EncepenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(
    SingletonComponent::class
)
object EncepenceModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): EncepenceDatabase {
        return Room.databaseBuilder(
            app,
            EncepenceDatabase::class.java,
            "EncepenceDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRepository(db: EncepenceDatabase): EncepenceRepository {
        return EncepenceRepositoryImpl(db)
    }
}