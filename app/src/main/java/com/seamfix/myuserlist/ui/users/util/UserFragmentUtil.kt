package com.seamfix.myuserlist.ui.users.util

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.seamfix.myuserlist.model.User
import com.seamfix.myuserlist.ui.users.UsersFragment
import kotlinx.android.synthetic.main.users_fragment.*


/*** This method controls the state of the UI ***/
fun UsersFragment.setUpUI(uiState: UIState, users: List<User>? = null){

    when(uiState){
        UIState.LOADING ->{
            //determines the UI  state when the app is loading data
            shimmer_view?.visibility = View.VISIBLE
            tvRetry?.visibility = View.INVISIBLE
            recyclerView?.visibility = View.INVISIBLE
            //show the refresh animation:
            swipeRefreshLayout.isRefreshing = true
        }
        UIState.DATA_FOUND ->{
            //determines the UI  state when the app has found data
            shimmer_view?.visibility = View.INVISIBLE
            tvRetry?.visibility = View.INVISIBLE
            recyclerView?.visibility = View.VISIBLE
            //hide the refresh animation:
            swipeRefreshLayout?.isRefreshing = false

            //populate the data on the recyclerView:
            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.orientation = LinearLayoutManager.VERTICAL;
            recyclerView?.layoutManager = layoutManager
            val adapter = UserAdapter(requireContext(), parentFragmentManager, users)
            recyclerView?.setHasFixedSize(true)
            recyclerView?.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        UIState.NO_DATA ->{
            //determines the UI  state when there is no data to load.
            shimmer_view?.visibility = View.INVISIBLE
            tvRetry?.visibility = View.VISIBLE
            recyclerView?.visibility = View.INVISIBLE
            //hide the refresh animation:
            swipeRefreshLayout?.isRefreshing = false
        }
    }
}


enum class UIState{
    LOADING,
    DATA_FOUND,
    NO_DATA
}