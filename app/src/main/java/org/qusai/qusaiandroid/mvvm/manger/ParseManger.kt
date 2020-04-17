package org.qusai.qusaiandroid.mvvm.manger

import com.google.gson.Gson
import org.json.JSONObject
import org.qusai.qusaiandroid.mvvm.model.BaseResponse

class ParseManger{
    constructor(){

    }
    fun getBaseResponse(json:JSONObject): BaseResponse {
        var gson:Gson=Gson()

        var baseResponse: BaseResponse =gson.fromJson(json.toString(),
            BaseResponse::class.java)

        return baseResponse
    }
 }