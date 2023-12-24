package com.example.task_adapter_2112.features

import android.app.AlertDialog
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task_adapter_2112.model.User

class MainViewModel : ViewModel() {
    var navigateToNextPage = MutableLiveData<Boolean>()
    private var userSiyahisi = mutableListOf<User>()
    var infoMessage = "To delete a user, please tap the user who you wanna delete"
    var toastUserDeleteMessage = "User deleted!"


    fun onNextPage() {
        navigateToNextPage.postValue(true)
    }

    fun yeniUserElaveEt(yUser : User): User? {
        if (yUser.ad.isEmpty()) {
            return null
        }
            userSiyahisi.add(yUser)
        return yUser
    }

}