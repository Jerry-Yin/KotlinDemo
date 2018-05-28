package com.jerryyin.kotlindemo.utils

import android.util.Log
import com.jerryyin.kotlindemo.interfaces.HttpConfigInterface
import com.jerryyin.kotlindemo.interfaces.OnResponseListener
import com.jerryyin.kotlindemo.model.MusicList
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

/**
 * @description http request utils class
 * @author JerryYin
 * @create 2018-05-24 16:51
 **/
class RetrofitHttpRequest {

    companion object {

        val TAG = "RetrofitHttpRequest.kt"

        /**
         * create Retrofit object
         */
        fun createRetrofit(url: String): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        fun httpGet(url: String, listener: OnResponseListener<String>) {
            val retrofit = createRetrofit(url)
            val httpConfigInterface = retrofit.create(HttpConfigInterface::class.java)

            val call = httpConfigInterface.httpGet()
            call.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(p0: Call<ResponseBody>?, p1: Throwable?) {
                    if (p1 != null) {
                        listener.onFailure(p1.hashCode(), p1.message!!)
                    }
                }

                override fun onResponse(p0: Call<ResponseBody>?, p1: Response<ResponseBody>?) {
                    if (p1 != null) {
                        if (p1.body() != null) {
                            var result = p1.body()!!.string()
                            Log.d(TAG, result)
//                            var p = p1.
                            listener.onSuccess(result)
                        } else {
//                            Log.d(TAG, p1.errorBody())
                            listener.onFailure(p1.code(), p1.errorBody()!!)
                        }
                    }
                }
            })

        }

    }


}