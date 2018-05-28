package com.jerryyin.kotlindemo.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.jerryyin.kotlindemo.R
import com.jerryyin.kotlindemo.base.KDConfig
import com.jerryyin.kotlindemo.interfaces.OnPlayingListener
import com.jerryyin.kotlindemo.interfaces.OnResponseListener
import com.jerryyin.kotlindemo.manager.KDMediaPlayerManager
import com.jerryyin.kotlindemo.model.MusicList
import com.jerryyin.kotlindemo.model.Vkeys
import com.jerryyin.kotlindemo.utils.OKHttpRequest
import kotlinx.android.synthetic.main.content_kdmusic_play.*

import kotlinx.android.synthetic.main.layout_activity_music_play.*
import java.lang.Exception

class KDMusicPlayActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "KDMusicPlayActivityActivity.kt"
    val URL_MUSIC = "http://dl.stream.qqmusic.qq.com/C4000031TAKo0095np.m4a?vkey=A8C1E35A1A9476F26C4278287B9580246C46070D1AC9756EA836391A0E3F89BC29790DFBB344CD04363C51613131D92D36A5C7B3073B12E4&guid=5767746091&uin=846597629&fromtag=66"

    private val MSG_GET_SONG_SRC_OK: Int = 0x011
    private val MSG_GET_SONG_SRC_FAIL: Int = 0x012


    var mMediaPlayerManager: KDMediaPlayerManager = KDMediaPlayerManager.getMediaPlayer(this)!!
    var mMid: String? = null
    var mSongSrc: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_music_play)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        initViews()
        initData();
    }

    private fun initViews() {
        btn_last_mc.setOnClickListener(this)
        btn_nexy_mc.setOnClickListener(this)
        btn_play_mc.setOnClickListener(this)
        btn_pause_mc.setOnClickListener(this)
    }

    private fun initData() {
//        getMusicList()
        mMid = this.intent.getStringExtra("mid")
        querySongSrc(mMid!!)
    }


    private fun querySongSrc(mid: String) {
//        val URL_TEST = "http://isure.stream.qqmusic.qq.com/C100"+mid+".m4a?fromtag=32"
        val urlSongVkey = KDConfig.URL_QQ_MUSIC_VKEY +
                "?g_tk=1910179786" +
                "&jsonpCallback=jsonCallback" +
                "&loginUin=846597629&hostUin=0" +
                "&format=json" +
                "&inCharset=utf8" +
                "&outCharset=utf-8" +
                "&notice=0" +
                "&platform=yqq" +
                "&needNewCode=0" +
                "&cid=205361747" +
                "&callback=jsonCallback" +
                "&uin=846597629" +
                "&songmid=" + mid +
                "&filename=C400" + mid + ".m4a" +
                "&guid=5767746091"
        OKHttpRequest.OKHttpGetAsync(urlSongVkey, null, object : OnResponseListener<String> {
            override fun onSuccess(result: String) {
                Log.d(TAG, result)
                var res = result.split("{\"code\":0")[1]
                res = "{\"code\":0" + res.substring(0, res.length - 1)
                Log.d(TAG, res)

                var message = Message()
                try {
                    var vkeys = Gson().fromJson<Vkeys>(res, Vkeys::class.java)
                    val vkey = vkeys.data.items[0].vkey
                    val urlSongSrc = KDConfig.URL_QQ_MUSIC_SONG_SRC + mMid + ".m4a" +
                            "?vkey=" + vkey +
                            "&guid=5767746091" +
                            "&uin=846597629" +
                            "&fromtag=66"
                    Log.d(TAG, urlSongSrc)

                    message.what = MSG_GET_SONG_SRC_OK
                    message.obj = urlSongSrc
                } catch (e: Exception) {
                    message.what = MSG_GET_SONG_SRC_FAIL
                    message.obj = resources.getString(R.string.tips_music_list_err1)
                }
                mHandler.handleMessage(message)

            }

            override fun onFailure(errorCode: Int, error: Any) {
                Log.d(TAG, error.toString())
            }
        })
    }

    private var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg!!.what) {
                MSG_GET_SONG_SRC_OK ->
                    praseSong(msg.obj.toString())

                MSG_GET_SONG_SRC_FAIL ->
                    queryFailed(msg.obj.toString())
            }
        }
    }

    private fun praseSong(src: String) {
        playMusicRemote(src)
    }

    private fun queryFailed(msg: String) {
        Toast.makeText(this@KDMusicPlayActivity, msg, Toast.LENGTH_SHORT).show()
    }


    override fun onClick(v: View?) {
        when (v) {
//            btn_last_mc -> getMusicList()
            btn_play_mc -> playMusicRemote(mSongSrc!!)
            btn_pause_mc -> pauseMusic()
        }
    }

    private fun playMusicRemote(url: String) {
        mSongSrc = url
        mMediaPlayerManager.play(url, null, 0, object : OnPlayingListener {
            override fun onProgress(progress: Int) {
                Log.d(TAG, "progress: " + progress)
            }

            override fun onCompletion(mp: MediaPlayer) {
                mp.release()
                Toast.makeText(this@KDMusicPlayActivity, "music playing is finished!", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun pauseMusic() {
        if (!mMediaPlayerManager.isPaused)
            mMediaPlayerManager.pause()
    }



}
