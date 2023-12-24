package com.example.task_adapter_2112.features

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.task_adapter_2112.adapters.UserListAdapter
import com.example.task_adapter_2112.databinding.ActivityMainBinding
import com.example.task_adapter_2112.model.User
import com.example.task_adapter_2112.Userlist.UserActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        adapter = UserListAdapter(this.baseContext, mutableListOf(), onDeleteUser = { user ->
           Toast.makeText(baseContext, viewModel.toastUserDeleteMessage, Toast.LENGTH_SHORT ).show()
            adapter.mainUserData.remove(user)
            adapter.notifyDataSetChanged()
        })
        binding.userlist.adapter = adapter

        binding.imageButton.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("Info")
                .setMessage(viewModel.infoMessage)
                .setPositiveButton("OK") { dialog, okay ->
                }.show()
        }
    }

        fun openAddUserActivity() {
            val intent = Intent(this, UserActivity::class.java)
            getMehsulList.launch(intent)
        }

        private val getMehsulList = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val item = result.data?.getParcelableExtra<User>("user")
                    item?.let {
                        viewModel.yeniUserElaveEt(it)?.let { user ->
                            adapter.yeniUserElave(user)
                        }
                    }
                }
            }

        fun observeAll() {
            viewModel.navigateToNextPage.observe(this) {
                if (it) {
                    openAddUserActivity()
                }
            }
        }

        fun removeObserveAll() {

            viewModel.navigateToNextPage.removeObservers(this)
            viewModel.navigateToNextPage.postValue(false)
        }

        override fun onResume() {
            super.onResume()
            observeAll()

        }

        override fun onPause() {
            super.onPause()
            removeObserveAll()
        }
    }


