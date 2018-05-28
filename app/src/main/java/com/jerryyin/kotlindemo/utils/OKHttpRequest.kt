package com.jerryyin.kotlindemo.utils

import android.util.Log
import com.jerryyin.kotlindemo.interfaces.OnResponseListener
import com.jerryyin.kotlindemo.model.RequestMap
import okhttp3.*
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


        /**
         * 异步请求
         *
         * @param url
         * @param headParams
         * @param listener
         */
        fun OKHttpGetAsync(url: String, headParams: List<RequestMap>?, listener: OnResponseListener<String>?) {
            try {
                Log.d(TAG, "url: $url")
                val client = OkHttpClient()
                val builder = Request.Builder()
                //            builder.addHeader("Content-Type", "application/json");
                if (headParams != null) {
                    Log.d(TAG, "headParams.size():" + headParams!!.size)
                    for (p in headParams) {
                        builder.addHeader(p.key, p.value.toString())
                        Log.d(TAG, "++ addHeader: " + p.key + ":" + p.value)
                    }
                }
                builder.url(url)
                        .get()
                        .build()
                val request = builder.build()
                val call = client.newCall(request)
                call.enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        if (listener != null) {
                            listener!!.onFailure(e.hashCode(), e.message!!)
                        }
                    }

                    @Throws(IOException::class)
                    override fun onResponse(call: Call, response: Response) {
                        if (listener != null) {
                            listener!!.onSuccess(response.body()!!.string())
                        }
                    }
                })
            } catch (e: NullPointerException) {
                if (listener != null) {
                    listener!!.onFailure(e.hashCode(), e.message!!)
                }
            }

        }


    }
}