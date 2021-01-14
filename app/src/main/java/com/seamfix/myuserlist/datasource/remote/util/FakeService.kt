package com.seamfix.myuserlist.datasource.remote.util

import com.seamfix.myuserlist.datasource.remote.Service
import com.seamfix.myuserlist.model.UserResponse
import retrofit2.Response

class FakeService : Service {

    override suspend fun fetchUsers(): Response<UserResponse> {
        return Response.success(null)
    }
}