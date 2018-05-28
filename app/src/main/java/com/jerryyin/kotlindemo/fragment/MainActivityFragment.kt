package com.jerryyin.kotlindemo.fragment

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jerryyin.kotlindemo.R
import com.jerryyin.kotlindemo.activity.KDMusicListActivity
import com.jerryyin.kotlindemo.activity.KDMusicPlayActivity
import com.jerryyin.kotlindemo.activity.RecyclerViewActivity
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), View.OnClickListener {

    private var mContentView: View? = null
    private var mSelf: Activity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (mContentView != null) {
            val vg: ViewGroup = mContentView!!.getParent() as ViewGroup
            vg.removeView(mContentView)
        } else {
            mSelf = activity
            mContentView = inflater.inflate(R.layout.fragment_main, container, false)
        }
        return mContentView
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        btn_recycler_view.setOnClickListener(this)
        btn_music.setOnClickListener(this)
        btn_music_box.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        when (v) {
            btn_recycler_view ->
                goToRecyclerViewPage()

            btn_music -> goToMusicPlayPage()

            btn_music_box -> toToMusicBox()
        }
    }

    private fun toToMusicBox() {
        val intent = Intent(mSelf, KDMusicListActivity::class.java)
        startActivity(intent)
    }

    private fun goToMusicPlayPage() {
        val intent = Intent(mSelf, KDMusicPlayActivity::class.java)
        startActivity(intent)

    }

    fun goToRecyclerViewPage() {
        val intent = Intent(mSelf, RecyclerViewActivity::class.java)
//        val intent: Intent = Intent()
//        intent.setClass(mSelf, RecyclerViewActivity::class.java)
        startActivity(intent)
    }
}
