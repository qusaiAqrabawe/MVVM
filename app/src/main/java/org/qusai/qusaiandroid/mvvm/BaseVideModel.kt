package org.qusai.qusaiandroid.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.qusai.qusaiandroid.mvvm.manger.ParseManger

open class BaseVideModel : AndroidViewModel {
    var prgressBarObservable:MutableLiveData<Boolean>?=null
    var parserManger: ParseManger?=null
    var app: Application?=null
    constructor(application: Application) : super(application) {
        prgressBarObservable= MutableLiveData()
        parserManger= ParseManger()
        this.app=application

    }

    fun setProgressBar(value:Boolean){
        prgressBarObservable?.value=value
    }
    fun getProgressValue():LiveData<Boolean>{

        return prgressBarObservable!!
    }


}