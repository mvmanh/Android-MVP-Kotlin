package com.mvm.kotlinmvplogin.ext

import android.content.Context
import android.widget.Toast
import es.dmoral.toasty.Toasty

fun Context.toast(message:CharSequence, time:Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, time).show()
}

fun Context.toasty(message:CharSequence, time:Int = Toasty.LENGTH_SHORT) {
    Toasty.normal(this, message, time).show()
}

fun Context.toastySuccess(message:CharSequence, time:Int = Toasty.LENGTH_SHORT) {
    Toasty.success(this, message, time).show()
}

fun Context.toastyWarning(message:CharSequence, time:Int = Toasty.LENGTH_SHORT) {
    Toasty.warning(this, message, time).show()
}