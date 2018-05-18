package com.jerryyin.kotlindemo.adapter

import android.content.Context
import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jerryyin.kotlindemo.R
import com.jerryyin.kotlindemo.interfaces.OnItemClickListener
import com.jerryyin.kotlindemo.model.ReNews
import kotlinx.android.synthetic.main.item_recycler_view1.view.*

class CusRecyclerViewAdapter(
        val c: Context,
        private val dataList: List<ReNews>
) : RecyclerView.Adapter<CusRecyclerViewAdapter.CustomViewHolder>() {

    var mClickListener: OnItemClickListener? = null;

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
            holder.index.text = news.index
            holder.content.text = news.content
            holder.check.isChecked = news.isCheck
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

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var index: TextView = view.txt_index
        var content: TextView = view.txt_content
        var check: AppCompatCheckBox = view.check
    }
}