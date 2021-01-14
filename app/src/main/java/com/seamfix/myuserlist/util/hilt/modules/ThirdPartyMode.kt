package com.seamfix.myuserlist.util.hilt.modules

import com.seamfix.myuserlist.data.UserRepository
import com.seamfix.myuserlist.datasource.remote.ApiClient
import com.seamfix.myuserlist.datasource.remote.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ThirdPartyMode {

    @Provides
    fun provideUserRepository(): UserRepository{
        val retrofit = ApiClient.getClient()
        val service: Service = retrofit.create(Service::class.java)
        val repository = UserRepository()
        repository.service = service
        return repository
    }
}