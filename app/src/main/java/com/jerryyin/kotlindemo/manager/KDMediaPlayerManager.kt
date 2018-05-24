package com.jerryyin.kotlindemo.manager

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log

import com.jerryyin.kotlindemo.interfaces.OnPlayingListener

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.Timer
import java.util.TimerTask

/**
 * Created by JerryYin on 11/10/15.
 * 用于MediaPlayer播放音频的管理类
 * 根据 官网 http://developer.android.com/intl/zh-cn/reference/android/media/MediaPlayer.html
 * 的MediaPlayer 流程方法编写状态图
 */
class KDMediaPlayerManager private constructor(private val mContext: Context) {

    companion object {

        private val TAG = "HiMediaPlayerManager"
        private var mInstance: KDMediaPlayerManager? = null


        fun getMediaPlayer(context: Context): KDMediaPlayerManager? {
            synchronized(KDMediaPlayerManager::class.java) {
                if (mInstance == null) {
                    mInstance = KDMediaPlayerManager(context)
                }
            }
            return mInstance
        }
    }

    private var mMediaPlayer: MediaPlayer? = null
    var isPaused: Boolean = false
    private var mTimer: Timer? = null


    init {
        init()
    }

    private fun init() {
        //        if (mMediaPlayer == null) {
        //            mMediaPlayer = new MediaPlayer();
        //            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
        //                @Override
        //                public boolean onError(MediaPlayer mp, int what, int extra) {
        //                    mMediaPlayer.reset();
        //                    return false;
        //                }
        //            });
        //        }
        //        setDataSource();

        if (mTimer == null)
            mTimer = Timer()
    }

    /**
     * 播放音频
     *
     * @param fileUrl  网络音频播放
     * @param filePath 从文件链接播放
     * @param musicId  内置音乐播放 (R.raw.voice)
     * @param listener 可以拿到播放进度以及
     */
    fun play(fileUrl: String?, filePath: String?, musicId: Int, listener: OnPlayingListener?) {
        if (mMediaPlayer != null)
            mMediaPlayer!!.reset()

        if (musicId != 0) {
            if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) {
                mMediaPlayer!!.stop()
                mMediaPlayer!!.release()
            }
            mMediaPlayer = MediaPlayer.create(mContext, musicId)
            //            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } else {
            mMediaPlayer = MediaPlayer()
            mMediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
            if (fileUrl != null) {
                try {
                    mMediaPlayer!!.setDataSource(fileUrl)

                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.e(TAG, e.message)
                }

            } else if (filePath != null) {
                try {
                    val file = File(filePath)
                    Log.d(TAG, "filePath:$filePath")
                    Log.d(TAG, "is file :" + file.isFile)
                    val fis = FileInputStream(file)
                    mMediaPlayer!!.setDataSource(fis.fd)

                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.e(TAG, e.message)
                }

            }
        }

        mMediaPlayer!!.setOnErrorListener { mp, what, extra ->
            mMediaPlayer!!.reset()
            false
        }


        try {
            mMediaPlayer!!.prepare()
            //            mMediaPlayer.prepareAsync();

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            Log.e(TAG, "IllegalStateException " + e.message)
        }

        mMediaPlayer!!.start()
        Log.d(TAG, "start playing...")

        if (listener != null)
            this.setOnPlayingListener(listener)
        // 此处播放音频出现过一个问题，具体参考： http://blog.csdn.net/zbcll2012/article/details/44020931

    }

    /**
     * 播放时的监听，可以单独使用方法，也可以直接播放时使用
     *
     * @param listener
     */
    fun setOnPlayingListener(listener: OnPlayingListener?) {
        if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) {
            if (listener != null) {
                mMediaPlayer!!.setOnCompletionListener(listener)
                if (mTimer != null) {
                    mTimer!!.schedule(object : TimerTask() {
                        override fun run() {
                            listener.onProgress(mMediaPlayer!!.currentPosition / 1000)
                            Log.d(TAG, "cur: " + mMediaPlayer!!.currentPosition / 1000)
                        }
                    }, 0, 100)
                }
            }
        }
    }

    /**
     * 暂停
     */
    fun pause() {
        if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) {
            mMediaPlayer!!.pause()
            isPaused = true
        }
    }

    /**
     * 恢复
     */
    fun resume() {
        if (mMediaPlayer != null && isPaused) {
            mMediaPlayer!!.start()
            isPaused = false
        }
    }

    /**
     * activity销毁时释放资源
     */
    fun release() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    val progress: Int
        get() {
            var progress = 0
            if (mMediaPlayer != null) {
                progress = mMediaPlayer!!.currentPosition / 1000
            }
            return progress
        }

    /**
     * minute =  (duration / 1000) / 60
     * second =  (duration / 1000) % 60
     *
     * @return
     */
    val maxProgress: Int
        get() {
            var max = 0
            if (mMediaPlayer != null) {
                max = mMediaPlayer!!.duration / 1000
            }
            return max
        }

    val isPlaying: Boolean
        get() = if (mMediaPlayer != null && mMediaPlayer!!.isPlaying)
            true
        else
            false

}
