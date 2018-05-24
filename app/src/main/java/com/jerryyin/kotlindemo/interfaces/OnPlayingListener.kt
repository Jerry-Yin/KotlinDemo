package com.jerryyin.kotlindemo.interfaces

import android.media.MediaPlayer

/**
 * Created by JerryYin on 4/27/17.
 */

interface OnPlayingListener : MediaPlayer.OnCompletionListener {

    fun onProgress(progress: Int)

    override fun onCompletion(mp: MediaPlayer)


}

