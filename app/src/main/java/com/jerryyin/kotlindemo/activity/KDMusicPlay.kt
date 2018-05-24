package com.jerryyin.kotlindemo.activity

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jerryyin.kotlindemo.R
import com.jerryyin.kotlindemo.interfaces.OnPlayingListener
import com.jerryyin.kotlindemo.manager.KDMediaPlayerManager
import kotlinx.android.synthetic.main.content_kdmusic_play.*

import kotlinx.android.synthetic.main.layout_activity_music_play.*

class KDMusicPlay : AppCompatActivity(), View.OnClickListener {

    val TAG = "KDMusicPlay.kt"
    val URL_MUSIC = "http://dl.stream.qqmusic.qq.com/C4000031TAKo0095np.m4a?vkey=A8C1E35A1A9476F26C4278287B9580246C46070D1AC9756EA836391A0E3F89BC29790DFBB344CD04363C51613131D92D36A5C7B3073B12E4&guid=5767746091&uin=846597629&fromtag=66"


    var mMediaPlayerManager: KDMediaPlayerManager = KDMediaPlayerManager.getMediaPlayer(this)!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_music_play)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        initViews()
    }

    private fun initViews() {
        btn_last_mc.setOnClickListener(this)
        btn_nexy_mc.setOnClickListener(this)
        btn_play_mc.setOnClickListener(this)
        btn_pause_mc.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v) {
            btn_play_mc -> playMusic()
            btn_pause_mc -> pauseMusic()

        }
    }


    private fun playMusic() {
        mMediaPlayerManager.play(URL_MUSIC, null, 0, object : OnPlayingListener {
            override fun onProgress(progress: Int) {
                Log.d(TAG, "progress: " + progress)
            }

            override fun onCompletion(mp: MediaPlayer) {
                mp.release()
                Toast.makeText(this@KDMusicPlay, "music playing is finished!", Toast.LENGTH_SHORT).show()
            }

        })

    }


    private fun pauseMusic() {
        if (!mMediaPlayerManager.isPaused)
            mMediaPlayerManager.pause()
    }
}
