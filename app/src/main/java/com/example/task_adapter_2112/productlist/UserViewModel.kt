package com.example.task_adapter_2112.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task_adapter_2112.model.User

class UserViewModel : ViewModel() {
    val adAdd = MutableLiveData<String>()
    val soyadAdd = MutableLiveData<String>()
    val errorText = MutableLiveData<String>()

    var navigateToHomePage = MutableLiveData<Boolean>()
    val newRegistrationCallback = MutableLiveData<Boolean>()

    fun onHomePage() {
        navigateToHomePage.postValue(true)
    }

    fun createNewUser() : User {
        return User(adAdd.value ?: "", soyadAdd.value ?: "")
    }

    fun onAddNewUser()  {
        when {
            adAdd.value.isNullOrEmpty() && soyadAdd.value.isNullOrEmpty() ->
                errorText.postValue("None can be empty")
            adAdd.value.isNullOrEmpty() ->
                errorText.postValue("Ad cannot be empty")
            soyadAdd.value.isNullOrEmpty() ->
                errorText.postValue("Soyad cannot be empty")
        }
        newRegistrationCallback.postValue(true)

    }
}