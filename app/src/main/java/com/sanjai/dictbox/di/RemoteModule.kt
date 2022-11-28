package com.sanjai.dictbox.di

import com.google.firebase.firestore.FirebaseFirestore
import com.sanjai.dictbox.data.api.DictionaryApi
import com.sanjai.dictbox.data.repository.DictionaryLocalDataSourceImpl
import com.sanjai.dictbox.data.repository.DictionaryRemoteDataSourceImpl
import com.sanjai.dictbox.domain.repository.DictionaryLocalDataSource
import com.sanjai.dictbox.domain.repository.DictionaryRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideDictionaryRemoteDataSourceImpl(
        api: DictionaryApi
    ): DictionaryRemoteDataSource {
        return DictionaryRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideLocalDataSourceImpl(
        db: FirebaseFirestore
    ): DictionaryLocalDataSource {
        return DictionaryLocalDataSourceImpl(db)
    }

}