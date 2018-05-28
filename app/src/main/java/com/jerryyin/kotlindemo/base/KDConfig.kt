package com.jerryyin.kotlindemo.base

/**
 * @description config file global pj
 * @author JerryYin
 * @create 2018-05-24 16:41
 **/
class KDConfig {

    companion object {

        // QQMusic
        // 获取某个歌手的音乐列表 ?ct=24&qqmusic_ver=1298&new_json=1&remoteplace=txt.yqq.song&searchid=69871941068007451&t=0&aggr=1&cr=1&catZhida=1&lossless=0&flag_qc=0&p=1&n=20&w=%E5%91%A8%E6%9D%B0%E4%BC%A6&g_tk=1910179786&jsonpCallback=MusicJsonCallback7523711350912596&loginUin=846597629&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0
        val URL_QQ_MUSCI_LIST = "https://c.y.qq.com/soso/fcgi-bin/client_search_cp"
        // 周杰伦 的音乐列表
        val URL_QQ_MUSIC_LIST_JAY = "https://c.y.qq.com/soso/fcgi-bin/"

    }

}