package com.seamfix.myuserlist.ui.users

import androidx.lifecycle.ViewModel
import com.seamfix.myuserlist.data.UserRepository
import com.seamfix.myuserlist.model.User

class UsersViewModel : ViewModel() {

    var userRepository: UserRepository? = null

    /***  Returns a list of users from either remote database or local database ***/
    suspend fun getUsers(): List<User>?{
        var users: List<User>? = null

        if(userRepository != null){
            //get users from remote database:
            users = userRepository?.getUsersFromRemote()

            if(users != null){
                //if we were able to fetch user's from  the service, then we need to save these users
                // in the local database for offline purpose:
                saveUsers(users)

            }else{//if no user was found, we fetch from local database:
                users =  userRepository?.getUsersLocally()
            }
        }
        return users
    }


    suspend fun saveUsers(users: List<User>){
        for(user in users){
            userRepository?.saveUser(user)
        }
    }
}