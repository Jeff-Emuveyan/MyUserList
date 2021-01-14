package com.seamfix.myuserlist.util.hilt

import com.seamfix.myuserlist.datasource.remote.Service
import com.seamfix.myuserlist.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class ServiceImpl @Inject constructor(): Service {
    override suspend fun fetchUsers(): Response<UserResponse> {
        return Response.success(null)
    }
}