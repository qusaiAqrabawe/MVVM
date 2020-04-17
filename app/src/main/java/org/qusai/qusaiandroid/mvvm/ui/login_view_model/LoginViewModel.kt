package org.qusai.qusaiandroid.mvvm.ui.login_view_model

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import org.qusai.qusaiandroid.mvvm.BaseVideModel
import org.qusai.qusaiandroid.mvvm.client.ApiClient
import org.qusai.qusaiandroid.mvvm.listener.ApiListener
import org.qusai.qusaiandroid.mvvm.model.BaseResponse

class LoginViewModel : BaseVideModel {
        var loginResponseObservalbe:MutableLiveData<BaseResponse>?=null
    var userName:MutableLiveData<String>
    var pas:MutableLiveData<String>

    constructor(application: Application) : super(application) {
        loginResponseObservalbe= MutableLiveData()
        userName= MutableLiveData()
        userName.value=""
        pas= MutableLiveData()
        pas.value=""


    }
 fun getPass():LiveData<String>{
     return  pas
 }
    fun getUserNames():LiveData<String>{
        return  userName
    }
    public fun iniViews(userNameComp:EditText,password:EditText){
        userNameComp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                userName.value=p0.toString()
            }
        })


        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                pas.value=p0.toString()
            }
        })
    }

     fun loginApi(listenerL: ApiListener){
          var body=HashMap<String,Any>()
         body.put("userName",getUserNames().value!!)
         body.put("password",getPass().value!!)
setProgressBar(true)
        ApiClient.instance .postRequest("login.php",body)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                setProgressBar(false)


                var json: JSONObject = JSONObject(it.string())

                loginResponseObservalbe!!.value=parserManger!!.getBaseResponse(json)
                listenerL.onDataReturn(loginResponseObservalbe!!.value!!)


            }, {
                setProgressBar(false)

                listenerL.onFailed("error")


            })


    }

}

