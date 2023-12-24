package com.example.task_adapter_2112.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val ad : String,
    val soyad : String
) : Parcelable {

    fun clickItem() {

    }

}

