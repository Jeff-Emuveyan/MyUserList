package com.seamfix.myuserlist.data

import com.seamfix.myuserlist.datasource.remote.Service
import com.seamfix.myuserlist.model.User
import com.seamfix.myuserlist.model.UserResponse
import javax.inject.Inject

class UserRepository @Inject constructor() {

    var service: Service? = null

    /***  Fetches a user from remote database ***/
    suspend fun getUserFromRemote(userID: String): User?{

        return try {
            val response = service?.fetchUser(userID) ?: return null
            if(response.code() == 200 && response.body() != null){ //response form server:
                val  userResponse = response.body() as User
                userResponse
            }else{//We assume that this is a network error:
                null
            }
        } catch (e: Exception) {
            null
        }
    }


    /***  Fetches a user locally from the database ***/
    suspend fun getUserLocally(userID: String): User?{
        return null
    }

    /***  Fetches users from remote database ***/
    suspend fun getUsersFromRemote(): ArrayList<User>?{

        return try {
            val response = service?.fetchUsers() ?: return null
            if(response.code() == 200 && response.body() != null){ //response form server:
                val  userResponse = response.body() as UserResponse
                userResponse.data?.toCollection(ArrayList())

            }else{//We assume that this is a network error:
                null
            }
        } catch (e: Exception) {
            null
        }
    }


    /***  Fetches users locally from the database ***/
    suspend fun getUsersLocally(): ArrayList<User>?{
        return null
    }
}