package com.mvm.kotlinmvplogin.base

interface MVP {

    interface BaseView<P>{
        fun setPresenter(presenter: P)
    }
    interface BasePresenter<V> {
        fun attachView(view: V)
        fun detachView()
    }
}