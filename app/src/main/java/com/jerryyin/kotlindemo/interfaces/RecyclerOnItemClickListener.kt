package com.jerryyin.kotlindemo.interfaces

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View

/**
 * @description 自定义 RecyclerView 点击事件监听器
 *  继承自 RecyclerView.OnItemTouchListener
 * @author JerryYin
 * @create 2018-05-17 16:36
 **/
class RecyclerOnItemClickListener(
        context: Context,
        recyclerView: RecyclerView,
        listener: OnItemClickListener
) : RecyclerView.OnItemTouchListener {

    var gestureDetector: GestureDetector? = null

    init {
        gestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {

            override fun onLongPress(e: MotionEvent?) {
                super.onLongPress(e)
                //long click
                if (e != null) {
                    val childView: View = recyclerView.findChildViewUnder(e.x, e.y)
                    listener!!.onItemLongClick(
                            childView,
                            recyclerView.getChildAdapterPosition(childView)
                    )
                }
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                //click
                if (e != null) {
                    println("e=${e}, listener=${listener}, view=${recyclerView}")
                    val childView: View = recyclerView.findChildViewUnder(e.x, e.y)
                    if (childView != null) {
                        println("childView = ${childView}")
                        listener!!.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
                        return true
                    }
                }
                return super.onSingleTapUp(e)
            }
        })
    }

    //事件的处理
    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     *  事件的拦截
     *  @return
     *      true: 拦截了 view 中子view的点击事件
     *      false： 不拦截
     */
    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
//        if (gestureDetector!!.onTouchEvent(e)) {
////            return true
//        }
        gestureDetector!!.onTouchEvent(e)
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}