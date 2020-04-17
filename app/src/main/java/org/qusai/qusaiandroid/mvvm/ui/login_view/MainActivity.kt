package org.qusai.qusaiandroid.mvvm.ui.login_view

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import org.qusai.qusaiandroid.mvvm.R
import org.qusai.qusaiandroid.mvvm.databinding.ActivityMainBinding
import org.qusai.qusaiandroid.mvvm.listener.ApiListener
import org.qusai.qusaiandroid.mvvm.listener.UiListner
import org.qusai.qusaiandroid.mvvm.model.BaseResponse
import org.qusai.qusaiandroid.mvvm.ui.login_view_model.LoginViewModel

class MainActivity : AppCompatActivity() {

    var listenerObj: ApiListener? = null
    var uiListenert: UiListner? = null
    var progressDialog: ProgressDialog? = null
    var loginViewModel: LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mainBindin: ActivityMainBinding
        mainBindin = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        loginViewModel = ViewModelProviders.of(this@MainActivity).get(LoginViewModel::class.java)
        uiListnert()
        loginListener()
        mainBindin.setLifecycleOwner(this)
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Loading....")
        mainBindin.loginViewModel = loginViewModel
        mainBindin.listener = listenerObj
        mainBindin.uiListener = uiListenert
        mainBindin.executePendingBindings()
        observeLoading()
        initObserver()
    }

    fun initObserver() {

        loginViewModel!!.iniViews(userName, pass)
    }

    fun observeLoading() {
        loginViewModel!!.getProgressValue().observe(this, object : Observer<Boolean> {
            override fun onChanged(t: Boolean?) {
                if (t!!) {
                    uiListenert!!.showLoading()
                } else
                    uiListenert!!.hideLoading()
            }
        })
    }

    fun loginListener() {
        listenerObj = object : ApiListener {

            override fun onDataReturn(obj: Any) {

                var res = obj as BaseResponse
                Toast.makeText(this@MainActivity, res.message, Toast.LENGTH_LONG).show()


            }

            override fun onFailed(message: String) {
                progressDialog!!.dismiss()

            }
        }
    }

    fun uiListnert() {
        uiListenert = object : UiListner {
            override fun showLoading() {
                progressDialog!!.show()
            }

            override fun hideLoading() {
                progressDialog!!.dismiss()
            }

        }
    }
}
