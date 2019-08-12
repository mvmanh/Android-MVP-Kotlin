package com.mvm.kotlinmvplogin.mvp

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.mvm.kotlinmvplogin.LoginModel
import com.mvm.kotlinmvplogin.R
import com.mvm.kotlinmvplogin.ext.MyTextWatcher
import com.mvm.kotlinmvplogin.ext.toastySuccess
import com.mvm.kotlinmvplogin.ext.toastyWarning
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoginMVP.View {

    private var mPresenter: LoginMVP.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter = LoginPresenter(this, LoginModel())
        initViewCallbacks()
    }

    private fun initViewCallbacks() {
        btnLogin.setOnClickListener {
            onLogin()
        }
        txtEmail.addTextChangedListener(object: MyTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                if (!TextUtils.isEmpty(s)) {
                    txtEmailLayout.error = null
                }
            }
        })

        txtPassword.addTextChangedListener(object : MyTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                if (!TextUtils.isEmpty(s)) {
                    txtPasswordLayout.error = null
                }
            }
        })
    }

    override fun onDestroy() {
        mPresenter?.let {
            it.destroy()
            mPresenter = null
        }
        super.onDestroy()
    }


    private fun onLogin() {
        closeKeyboard()
        if (!isInputValid()) {
            return
        }
        mPresenter?.login(txtEmail.text.toString(), txtPassword.text.toString())
    }

    private fun closeKeyboard() {
        txtEmail.onEditorAction(EditorInfo.IME_ACTION_DONE)
        txtPassword.onEditorAction(EditorInfo.IME_ACTION_DONE)
    }

    override fun onLoginSuccess() {
        toastySuccess("Login success! Close the application")
        finish()
    }

    override fun onLoginFail(reason: String) {
        toastyWarning(reason)
    }

    private fun isInputValid():Boolean {
        val email = txtEmail.text.toString()
        val password = txtPassword.text.toString()

        if (TextUtils.isEmpty(email)) {
            txtEmail.requestFocus()
            txtEmailLayout.error = "Please enter your email"
            return false
        }
        else if (TextUtils.isEmpty(password)) {
            txtPassword.requestFocus()
            txtEmailLayout.error = "Please enter your password"
            return false
        }
        txtEmailLayout.error = null
        txtPasswordLayout.error = null
        return true
    }
}

