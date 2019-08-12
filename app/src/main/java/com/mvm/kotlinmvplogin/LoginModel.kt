package com.mvm.kotlinmvplogin

import android.text.TextUtils
import java.security.InvalidParameterException

class LoginModel{

    interface Callback {
        fun onLoginSuccess()
        fun onLoginFail(t: Throwable)
    }

    fun login(email: String, password: String, callback: Callback?) {
        
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            callback?.onLoginFail(InvalidParameterException("Email and password should not be empty"))
        }
        else if (email == "mvmanh@gmail.com" && password == "111111") {
            callback?.onLoginSuccess()
        }
        else if (email == "mvmanh@gmail.com" && password == "!") {
            callback?.onLoginFail(UnsupportedOperationException("Server got an unexpected error"))
        }else {
            callback?.onLoginFail(RuntimeException("Email or password does not correct"))
        }
    }

}