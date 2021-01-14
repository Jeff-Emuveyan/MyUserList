package com.seamfix.myuserlist.util.hilt.modules

import com.seamfix.myuserlist.datasource.remote.Service
import com.seamfix.myuserlist.util.hilt.ServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class LocalModule {

    @Binds
    abstract fun createService(serviceImpl: ServiceImpl): Service
}