package com.jerryyin.kotlindemo.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.jerryyin.kotlindemo.R
import com.jerryyin.kotlindemo.adapter.MusicListAdapter
import com.jerryyin.kotlindemo.base.KDConfig
import com.jerryyin.kotlindemo.interfaces.OnItemClickListener
import com.jerryyin.kotlindemo.interfaces.OnResponseListener
import com.jerryyin.kotlindemo.model.MusicList
import com.jerryyin.kotlindemo.utils.RetrofitHttpRequest
import kotlinx.android.synthetic.main.layout_activity_music_list.*

import java.lang.Exception

class KDMusicListActivity : AppCompatActivity() {


    val TAG = "KDMusicListActivity.kt"

    val MSG_GET_MUSIC_LIST_OK = 0X01
    val MSG_GET_MUSIC_LIST_FAIL = 0X02


    private var mMusicList: MusicList? = null
    private var mSongList : List<MusicList.DataBean.SongBean.ListBean>? = null
    private var mMusicAdapter: MusicListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_music_list)

        initViews()
        initData()

    }

    private fun initViews() {
        loading_view.visibility = View.VISIBLE
    }


    private fun initData() {
        getMusicList()
    }


    private fun getMusicList() {
        RetrofitHttpRequest.httpGet(KDConfig.URL_QQ_MUSIC_LIST_JAY, object : OnResponseListener<String> {
            override fun onSuccess(result: String) {
//                MusicJsonCallback7523711350912596({"code":0,"data":{"keyword":"周杰伦","priority":0,"qc":[],...)
                Log.d(TAG, result)
                if (!result.isEmpty()) {
                    var res = result.split("{\"code\":0")[1]
                    res = "{\"code\":0" + res.substring(0, res.length - 1)
                    Log.d(TAG, res)
                    var message = Message()
                    try {
                        var jsonObject = Gson().fromJson<MusicList>(res, MusicList::class.java)
                        message.what = MSG_GET_MUSIC_LIST_OK
                        message.obj = jsonObject
                    } catch (e: Exception) {
                        message.what = MSG_GET_MUSIC_LIST_FAIL
                        message.obj = resources.getString(R.string.tips_music_list_err1)
                    }
                    mHandler.handleMessage(message)
                }
            }

            override fun onFailure(errorCode: Int, error: Any) {
                var message = Message()
                message.what = MSG_GET_MUSIC_LIST_FAIL
                message.obj = error as String
                mHandler.handleMessage(message)
            }
        })
    }

    private var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg!!.what) {
                MSG_GET_MUSIC_LIST_OK ->
                    praseMusicList(msg.obj as MusicList)

                MSG_GET_MUSIC_LIST_FAIL ->
                    queryFailed(msg.obj.toString())
            }
        }
    }

    private fun queryFailed(msg: String) {
        loading_view.visibility = View.GONE
        Toast.makeText(this@KDMusicListActivity, msg, Toast.LENGTH_SHORT).show()
    }


    private fun praseMusicList(obj: MusicList) {
        mMusicList = obj
        val list : List<MusicList.DataBean.SongBean.ListBean> = mMusicList!!.data!!.song!!.list!!
        mSongList = list
        mMusicAdapter = MusicListAdapter(this, mSongList!!)
        rv_music_list.layoutManager = LinearLayoutManager(this)
        rv_music_list.adapter = mMusicAdapter
        mMusicAdapter!!.notifyDataSetChanged()
        loading_view.visibility = View.GONE

        mMusicAdapter!!.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                var intent = Intent(this@KDMusicListActivity, KDMusicPlayActivity::class.java)
                startActivity(intent)
            }

            override fun onItemLongClick(view: View, position: Int) {
                TODO()
            }

        })
    }
}
