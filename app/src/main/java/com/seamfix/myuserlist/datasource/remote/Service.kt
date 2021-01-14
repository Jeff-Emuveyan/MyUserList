package com.seamfix.myuserlist.datasource.remote

import com.seamfix.myuserlist.model.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface Service {

    @GET("/data/api/user?limit=100")
    suspend fun fetchUsers(): Response<UserResponse>


}