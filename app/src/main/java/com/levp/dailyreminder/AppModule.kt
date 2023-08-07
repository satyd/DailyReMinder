package com.levp.dailyreminder

import android.app.Application
import androidx.room.Room
import com.levp.dailyreminder.database.ReminderDatabase
import com.levp.dailyreminder.database.ReminderRepository
import com.levp.dailyreminder.database.ReminderRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideReminderDatabase(app: Application): ReminderDatabase {
        return Room.databaseBuilder(
            app,
            ReminderDatabase::class.java,
            "reminder_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideReminderRepository(db: ReminderDatabase): ReminderRepository {
        return ReminderRepositoryImpl(db.dao)
    }
}