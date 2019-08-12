package com.mvm.kotlinmvplogin.mvp

import com.mvm.kotlinmvplogin.LoginModel

class LoginPresenter(private var view: LoginMVP.View?, private val model: LoginModel):
    LoginMVP.Presenter {

    override fun destroy() {
        this.view = null
    }

    override fun login(email: String, password: String) {
        model.login(email, password, object: LoginModel.Callback {
            override fun onLoginSuccess() {
                view?.onLoginSuccess()
            }

            override fun onLoginFail(t: Throwable) {
                view?.onLoginFail(t.message?: "Unknown error has occured")
            }

        })
    }

}