package com.nerinos.todolistapp.di

import android.app.Application
import androidx.room.Room
import com.nerinos.todolistapp.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class) // one object for Application
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app:Application,
        callback: TaskDatabase.Callback
    ) = Room.databaseBuilder(app, TaskDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    fun provideTaskDao(db: TaskDatabase) = db.taskDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob()) // if coroutine child has failed, keep the others running
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope