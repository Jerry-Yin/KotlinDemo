package com.jerryyin.kotlindemo.utils

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

/**
 * @description okhttp3.10.0
 * @author JerryYin
 * @create 2018-05-25 15:49
 **/
class OKHttpRequest {

    companion object {

        val TAG = "OKHttpRequest.kt"


        /**
         * 同步http get 请求
         *
         * @param url
         */
        fun httpGetSync(url: String): String {
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url(url)
                    .build()
            val response = client.newCall(request).execute()
            return response.body()!!.string()
        }





    }
}