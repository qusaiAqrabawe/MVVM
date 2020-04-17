package org.qusai.qusaiandroid.mvvm.data

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

interface Services {


    @POST
    @FormUrlEncoded
    fun postRequest(@Url apiName:String,@FieldMap obj:HashMap<String,Any>):Observable<ResponseBody>
}