package com.jerryyin.kotlindemo.interfaces;

import org.json.JSONObject;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HttpConfigInterface {

    @GET("client_search_cp?ct=24&qqmusic_ver=1298&new_json=1&remoteplace=txt.yqq.song&searchid=69871941068007451&t=0&aggr=1&cr=1&catZhida=1&lossless=0&flag_qc=0&p=1&n=20&w=%E5%91%A8%E6%9D%B0%E4%BC%A6&g_tk=1910179786&jsonpCallback=MusicJsonCallback7523711350912596&loginUin=846597629&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0")
    Call<ResponseBody> httpGet();

}
