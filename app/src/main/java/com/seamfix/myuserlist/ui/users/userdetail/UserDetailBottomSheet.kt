package com.seamfix.myuserlist.ui.users.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.seamfix.myuserlist.R
import com.seamfix.myuserlist.data.UserRepository
import com.seamfix.myuserlist.model.User
import com.seamfix.myuserlist.ui.users.util.UIState
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.user_detail_bottom_sheet.*
import kotlinx.android.synthetic.main.user_detail_has_data.view.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailBottomSheet(var user: User) : BottomSheetDialogFragment() {

    //Inject the repository
    @Inject
    lateinit var repo: UserRepository

    private lateinit var viewModel: UserDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_detail_bottom_sheet, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //needed to remove the white background color of the bottom sheet.
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)
        viewModel.userRepository = repo

        //We start fetching the user:
        lifecycleScope.launch {
            getUser(user.id)
        }
    }


    private suspend fun getUser(userID: String){
        setUpUIState(UIState.LOADING)

        val user = viewModel.getUser(userID)
        if(user != null){
            setUpUIState(UIState.DATA_FOUND,user)
        }else{
            setUpUIState(UIState.NO_DATA)
        }
    }


    private fun setUpUIState(uiState: UIState, user: User? = null){
        when(uiState){
            UIState.LOADING ->{
                view_no_data?.visibility = View.VISIBLE
                view_has_data?.visibility = View.GONE
                tvError?.visibility = View.GONE
            }
            UIState.DATA_FOUND ->{
                view_no_data?.visibility = View.GONE
                view_has_data?.visibility = View.VISIBLE
                tvError?.visibility = View.GONE

                //bind the data to the views:
                view_has_data?.name?.text =
                        requireContext().
                        getString(R.string.user_full_name, user?.title, user?.firstName, user?.lastName);
                view_has_data?.gender?.text = "${user?.gender}"
                view_has_data?.email?.text = "${user?.email}"
                view_has_data?.dob?.text = "${user?.dateOfBirth}"
                view_has_data?.reg?.text = "${user?.registerDate}"
                view_has_data?.location?.text = "${user?.location?.toString()}"

                //load the profile photo
                Picasso.get().load(user?.picture).placeholder(R.drawable.person)
                        .error(R.drawable.ic_broken_image).into(view_has_data?.ivPhoto)
            }
            UIState.NO_DATA ->{
                view_no_data?.visibility = View.GONE
                view_has_data?.visibility = View.GONE
                tvError?.visibility = View.VISIBLE
            }
        }
    }

}