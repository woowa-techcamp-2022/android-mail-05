package com.example.mailapp.di

import com.example.mailapp.data.source.FakeMailDataSource
import com.example.mailapp.data.source.MailDataSource
import com.example.mailapp.data.source.MailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMailRepository(
        mailDataSource: MailDataSource
    ): MailRepository {
        return MailRepository(mailDataSource)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMailDataSource(): MailDataSource = FakeMailDataSource()
}