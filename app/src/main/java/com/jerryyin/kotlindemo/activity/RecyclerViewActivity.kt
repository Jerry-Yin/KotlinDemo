package com.jerryyin.kotlindemo.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jerryyin.kotlindemo.R
import com.jerryyin.kotlindemo.adapter.CusRecyclerViewAdapter
import com.jerryyin.kotlindemo.interfaces.OnItemClickListener
import com.jerryyin.kotlindemo.model.ReNews
import com.jerryyin.kotlindemo.utils.DateUtil
import kotlinx.android.synthetic.main.item_recycler_view1.*
import kotlinx.android.synthetic.main.layout_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    companion object {
        val TAG = "RecyclerViewActivity.class"

        val KEY_CURRENT_NEWS = "currentNews"
        val KEY_NEWS_ID = "id"
        val KEY_NEWS_IMG = "image"
        val KEY_NEWS_TITLE = "title"
        val KEY_NEWS_TIME = "time"
    }


    private var mNewsList: ArrayList<ReNews> = ArrayList()
    private var mRecyclerViewAdapter: CusRecyclerViewAdapter? = null
    private val mImageIds = listOf<Int>(R.mipmap.img_couple, R.mipmap.img_family, R.mipmap.img_jump, R.mipmap.img_travel, R.mipmap.img_snow)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_recycler_view)
        initData()
        initViews()
    }

    private fun initData() {
        for (i in 1..20) {
            mNewsList.add(ReNews(
                    i,
                    mImageIds[(Math.random() * 4).toInt()],
//                    R.mipmap.img_jump,
                    "Top beaches $i to visit !",
                    DateUtil.stampToDate(System.currentTimeMillis().toString())))
        }
    }

    private fun initViews() {
        mRecyclerViewAdapter = CusRecyclerViewAdapter(this, mNewsList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = mRecyclerViewAdapter
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recycler_view.setHasFixedSize(true)

        //点击事件实现方式1： 自定义recyclerview的触摸事件 OnItemTouchListener
//        recycler_view.addOnItemTouchListener(RecyclerOnItemClickListener(this, recycler_view, object : OnItemClickListener {
//            @SuppressLint("ShowToast", "LongLogTag")
//            override fun onItemClick(view: View, position: Int) {
//                Toast.makeText(this@RecyclerViewActivity, "click item ${position}!", Toast.LENGTH_SHORT).show()
//                Log.d(TAG, "click item ${position}!")
//            }
//
//
//            @SuppressLint("ShowToast", "LongLogTag")
//            override fun onItemLongClick(view: View, position: Int) {
//                Toast.makeText(this@RecyclerViewActivity, "long click item ${position}!", Toast.LENGTH_SHORT).show()
//                println("long click item ${position}!")
//                Log.d(TAG, "long click item ${position}!")
//            }
//
//        }))


        //点击事件实现方式2  adapter里面添为itemView 添加 setOnClicklistener()
        mRecyclerViewAdapter!!.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(this@RecyclerViewActivity, "click item ${position}!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "click item ${position}!")
                var intent = Intent(this@RecyclerViewActivity, KDNewsDetailActivity::class.java)
                //场景动画页面跳转
//                var imagePair = Pair<View, String>(img_topic, resources.getString(R.string.trans_img_topic))
//                var txtTitle = Pair<View, String>(txt_title, "txtTitle")
                var optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@RecyclerViewActivity, img_topic, getString(R.string.trans_img_topic))

                //界面间传值
                var bundle: Bundle = Bundle()
                bundle.putInt(KEY_NEWS_ID, position)
                bundle.putString(KEY_NEWS_TITLE, mNewsList[position].title)
                bundle.putInt(KEY_NEWS_IMG, mNewsList[position].imgId!!)
                bundle.putString(KEY_NEWS_TIME, mNewsList[position].time)
                intent.putExtra(KEY_CURRENT_NEWS, bundle)
                ActivityCompat.startActivity(this@RecyclerViewActivity, intent, optionsCompat.toBundle());
            }

            override fun onItemLongClick(view: View, position: Int) {
                Toast.makeText(this@RecyclerViewActivity, "long click item ${position}!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "long click item ${position}!")
            }

        })

    }


}
