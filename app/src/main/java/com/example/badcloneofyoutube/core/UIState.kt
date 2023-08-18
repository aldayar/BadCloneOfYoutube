package com.example.badcloneofyoutube.core

sealed class UIState<T>(
    val data:T? = null,
    val msg:String? = null
){
    class Loading<T>():UIState<T>()
    class Success <T>(data: T?):UIState<T>(data = data)
    class Error<T> (msg: String?):UIState<T>(msg = msg)
}