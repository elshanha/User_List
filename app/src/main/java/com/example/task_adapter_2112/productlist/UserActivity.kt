package com.example.task_adapter_2112.productlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.task_adapter_2112.databinding.UserElaveBinding

class UserActivity : AppCompatActivity() {

    lateinit var binding: UserElaveBinding
    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding = UserElaveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    private fun observeAll() {
        viewModel.navigateToHomePage.observe(this) {
            if (it) {
                    finish()
            }
        }

        viewModel.errorText.observe(this) {
            if (!it.isNullOrEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.newRegistrationCallback.observe(this) {
            val intent = Intent()
            val user = viewModel.createNewUser()
            intent.putExtra("user", user)
            setResult(RESULT_OK, intent)
            if (user.ad.isNullOrEmpty() || user.soyad.isNullOrEmpty()) {
                onPause()
            } else {
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        observeAll()
    }

}