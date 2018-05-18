package com.jerryyin.kotlindemo.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.jerryyin.kotlindemo.R
import com.jerryyin.kotlindemo.R.id.recycler_view
import com.jerryyin.kotlindemo.adapter.CusRecyclerViewAdapter
import com.jerryyin.kotlindemo.interfaces.OnItemClickListener
import com.jerryyin.kotlindemo.interfaces.RecyclerOnItemClickListener
import com.jerryyin.kotlindemo.model.ReNews
import kotlinx.android.synthetic.main.layout_recycler_view.*
import kotlin.math.log

class RecyclerViewActivity : AppCompatActivity() {


    val TAG = "RecyclerViewActivity.class"

    private var mNewsList: ArrayList<ReNews> = ArrayList()
    private var mRecyclerViewAdapter: CusRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_recycler_view)
        initData()
        initViews()
    }

    private fun initData() {
        for (i in 1..20) {
            mNewsList.add(ReNews(
                    i.toString(),
                    "this is the number $i content!",
                    i % 2 == 0))
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
            }

            override fun onItemLongClick(view: View, position: Int) {
                Toast.makeText(this@RecyclerViewActivity, "long click item ${position}!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "long click item ${position}!")
            }

        })

    }


}
