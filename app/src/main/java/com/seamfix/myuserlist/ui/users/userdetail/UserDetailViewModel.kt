package com.seamfix.myuserlist.ui.users.userdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.seamfix.myuserlist.data.UserRepository
import com.seamfix.myuserlist.model.User
import javax.inject.Inject

class UserDetailViewModel @Inject constructor() : ViewModel() {

    var userRepository: UserRepository? = null

    /***  Returns a user from either remote database or local database ***/
    suspend fun getUser(userID: String): User? {
        var user: User? = null

        if(userRepository != null){
            //get user from remote database:
            user = userRepository?.getUserFromRemote(userID)

            if(user == null){//if no user was found, we fetch from local database:
                user = userRepository?.getUserLocally(userID)
            }
        }
        return user
    }
}