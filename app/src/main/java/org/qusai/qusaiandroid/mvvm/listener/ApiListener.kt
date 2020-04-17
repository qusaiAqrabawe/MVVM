package org.qusai.qusaiandroid.mvvm.listener

interface ApiListener {
    fun onDataReturn(obj:Any)
    fun onFailed(message:String)

}