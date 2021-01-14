package com.seamfix.myuserlist.ui.users.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seamfix.myuserlist.R
import com.seamfix.myuserlist.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class UserAdapter() : RecyclerView.Adapter<UserItem>() {

    lateinit var context: Context
    var users: List<User>? = null

    constructor(context: Context, users: List<User>?): this(){
        this.context = context
        this.users = users
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItem {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.user_item, parent, false)
        return UserItem(view)
    }

    override fun onBindViewHolder(holder: UserItem, position: Int) {

        //get a user:
        users?.let {
            val user: User = it[position]

            //bind the data to the view:
            holder.tvName.text = user.firstName
            holder.tvLName.text = user.lastName
            holder.tvEmail.text = user.email

            //now we need to load the user's profile picture:
            holder.shimmerView.showShimmer(true)
            holder.shimmerView.startShimmer()
            Picasso.get().load(user.picture).placeholder(R.drawable.person)
                .error(R.drawable.ic_broken_image).into(holder.imageView, object : Callback {
                    override fun onError(e: Exception?) {
                        holder.shimmerView.stopShimmer()
                        holder.shimmerView.hideShimmer()
                        holder.imageView.setImageResource(R.drawable.ic_broken_image)
                    }
                    override fun onSuccess() {
                        holder.shimmerView.stopShimmer()
                        holder.shimmerView.hideShimmer()
                    }
                })

        }
    }


    override fun getItemCount(): Int {
        return users?.size ?: 0
    }

}
