package com.jerryyin.kotlindemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jerryyin.kotlindemo.R
import com.jerryyin.kotlindemo.adapter.CusRecyclerViewAdapter
import com.jerryyin.kotlindemo.interfaces.OnItemClickListener
import com.jerryyin.kotlindemo.model.ReNews
import com.jerryyin.kotlindemo.utils.DateUtil
import kotlinx.android.synthetic.main.layout_recycler_view.*
import java.sql.Time

class RecyclerViewActivity : AppCompatActivity() {


    val mTAG = "RecyclerViewActivity.class"

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
                    mImageIds[(Math.random()*4).toInt()],
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
//                Log.d(mTAG, "click item ${position}!")
//            }
//
//
//            @SuppressLint("ShowToast", "LongLogTag")
//            override fun onItemLongClick(view: View, position: Int) {
//                Toast.makeText(this@RecyclerViewActivity, "long click item ${position}!", Toast.LENGTH_SHORT).show()
//                println("long click item ${position}!")
//                Log.d(mTAG, "long click item ${position}!")
//            }
//
//        }))


        //点击事件实现方式2  adapter里面添为itemView 添加 setOnClicklistener()
        mRecyclerViewAdapter!!.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(this@RecyclerViewActivity, "click item ${position}!", Toast.LENGTH_SHORT).show()
                Log.d(mTAG, "click item ${position}!")
            }

            override fun onItemLongClick(view: View, position: Int) {
                Toast.makeText(this@RecyclerViewActivity, "long click item ${position}!", Toast.LENGTH_SHORT).show()
                Log.d(mTAG, "long click item ${position}!")
            }

        })

    }


}
