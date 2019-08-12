package com.mvm.kotlinmvplogin.mvp

interface LoginMVP {
    open interface View {
        fun onLoginSuccess()
        fun onLoginFail(reason:String)
    }

    open interface Presenter {
        fun destroy()
        fun login(email:String, password:String)
    }

}