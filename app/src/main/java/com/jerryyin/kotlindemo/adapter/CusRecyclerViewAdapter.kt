package com.jerryyin.kotlindemo.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.jerryyin.kotlindemo.R
import com.jerryyin.kotlindemo.interfaces.OnItemClickListener
import com.jerryyin.kotlindemo.model.ReNews
import kotlinx.android.synthetic.main.item_recycler_view1.view.*

class CusRecyclerViewAdapter(
        val c: Context,
        private val dataList: List<ReNews>
) : RecyclerView.Adapter<CusRecyclerViewAdapter.CustomViewHolder>() {


    val TAG = "CusRecyclerViewAdapter.class"
    var mClickListener: OnItemClickListener? = null;

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img: SimpleDraweeView = view.img_topic
        var title: TextView = view.txt_title
        var time: TextView = view.txt_time
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view: View = LayoutInflater.from(c).inflate(R.layout.item_recycler_view1, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val news: ReNews = dataList.get(position)
        if (holder != null) {

//            Log.d(TAG, c.resources.getDrawable(news.imgId!!).toString())
//            Log.d(TAG, news.imgId.toString())
//            Log.d(TAG, c.resources.getString(news.imgId!!))
//            Log.d(TAG, c.getString(news.imgId!!))
//            Log.d(TAG, "res://"+c.packageName+"/R.mipmap.img_family")
//            Log.d(TAG, Uri.parse("res://"+c.packageName+"/R.mipmap.img_family").toString())

            holder.img.setImageDrawable(c.resources.getDrawable(news.imgId!!))
            holder.title.text = news.title
            holder.time.text = news.time
            if (mClickListener != null) {
                holder.itemView.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        mClickListener!!.onItemClick(v!!, position)
                    }
                })
                holder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
                    override fun onLongClick(v: View?): Boolean {
                        mClickListener!!.onItemLongClick(v!!, position)
                        //return true -> 点击事件已经被消费，不会继续出发点击事件；  else false -> 继续触发点击事件
                        return true
                    }
                })
            }
        }
    }

    //ViewHolder复用的时候调用
    override fun onViewRecycled(holder: CustomViewHolder?) {
        super.onViewRecycled(holder)

    }
}