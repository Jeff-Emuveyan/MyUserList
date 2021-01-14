package com.seamfix.myuserlist.ui.users

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.seamfix.myuserlist.R
import com.seamfix.myuserlist.data.UserRepository
import com.seamfix.myuserlist.ui.users.util.UIState
import com.seamfix.myuserlist.ui.users.util.setUpUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.users_fragment.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class UsersFragment : Fragment() {

    //Inject the repository
    @Inject lateinit var repo: UserRepository

    companion object {
        fun newInstance() = UsersFragment()
    }

    private lateinit var viewModel: UsersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        viewModel.userRepository = repo

        swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch{
                displayUsers(viewModel)
            }
        }

        //When the fragment loads, we display the users:
        lifecycleScope.launch{
            displayUsers(viewModel)
        }
    }


    /*** Fetches users and displays them in the recyclerView ***/
    private suspend fun displayUsers(viewModel: UsersViewModel) {
        setUpUI(UIState.LOADING)
        //make request to fetch users:
        val users = viewModel.getUsers()

        if(users == null){
            //No user was found:
            setUpUI(UIState.NO_DATA)
        }else{
            setUpUI(UIState.DATA_FOUND, users)
        }
    }

}