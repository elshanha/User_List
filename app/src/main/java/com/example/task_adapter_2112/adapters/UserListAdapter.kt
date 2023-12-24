package com.example.task_adapter_2112.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.task_adapter_2112.R
import com.example.task_adapter_2112.databinding.CardViewBinding
import com.example.task_adapter_2112.model.User

class UserListAdapter(val context : Context, private var dataSiyahi : MutableList<User>, var onDeleteUser: (User) -> Unit) : BaseAdapter() {

    var mainUserData = dataSiyahi

    fun yeniUserElave(yUser: User) {
        dataSiyahi.add(yUser)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return dataSiyahi.count()
    }

    override fun getItem(position: Int): Any {
        return dataSiyahi[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, contentView: View?, parent: ViewGroup?): View {

        var yeniContentView = contentView
        var holder: ViewHolder

        if (contentView == null) {
            val binding: CardViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.card_view,
                parent,
                false
            )
            yeniContentView = binding.root

            holder = ViewHolder(binding, onDeleteUser)
            holder.bind(dataSiyahi[position])
            yeniContentView?.tag = holder

        } else {
            holder = contentView.tag as ViewHolder
            holder.bind(dataSiyahi[position])
        }
        return yeniContentView!!
    }

    private class ViewHolder(var binding: CardViewBinding, var onListClick: (User) -> Unit) {

        fun bind(user: User) {
            binding.ad.text = user.ad
            binding.soyad.text = user.soyad
            binding.user = user

            binding.root.setOnClickListener {
                onListClick(binding.user as User)
            }
        }
    }
}