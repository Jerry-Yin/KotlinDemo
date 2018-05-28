package com.jerryyin.kotlindemo.adapter

import android.content.Context
import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jerryyin.kotlindemo.R
import com.jerryyin.kotlindemo.interfaces.OnItemClickListener
import com.jerryyin.kotlindemo.model.MusicList.DataBean.SongBean.ListBean
import kotlinx.android.synthetic.main.item_song.view.*

/**
 * @description music list for recycler view
 * @author JerryYin
 * @create 2018-05-28 10:39
 **/
class MusicListAdapter(
        val c: Context,
        private val dataList: List<ListBean>
) : RecyclerView.Adapter<MusicListAdapter.MusicViewHolder>() {

    val TAG = "CusRecyclerViewAdapter.class"
    var mClickListener: OnItemClickListener? = null;


    class MusicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id = view.txt_song_index
        var img = view.img_song
        var song_name = view.txt_song_name
        var is_sq = view.icon_sq
        var is_only = view.icon_property
        var album = view.txt_album
        var have_mv = view.btn_song_mv
        var more = view.btn_song_more
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MusicViewHolder {
        val view: View = LayoutInflater.from(c).inflate(R.layout.item_song, parent, false)
        return MusicListAdapter.MusicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MusicViewHolder?, position: Int) {
        val music = dataList.get(position)
        if (holder != null) {
            if (position <= 8) {
                holder.id.text = "0" + (position + 1).toString()
            } else {
                holder.id.text = (position + 1).toString()
            }
            holder.img.setImageURI("https://y.gtimg.cn/music/photo_new/T001R300x300M0000025NhlN2yWrP4.jpg?max_age=2592000")
            holder.song_name.text = music.name
            if (music.isonly == 1) {
                holder.is_only.visibility = View.VISIBLE
            } else {
                holder.is_only.visibility = View.GONE
            }
            holder.album.text = music.album!!.name
            if (music.mv != null) {
                holder.have_mv.visibility = View.VISIBLE
            } else {
                holder.have_mv.visibility = View.INVISIBLE
            }

            if (mClickListener != null) {
                holder.itemView.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        mClickListener!!.onItemClick(v!!, position)
                    }
                })
//                holder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
//                    override fun onLongClick(v: View?): Boolean {
//                        mClickListener!!.onItemLongClick(v!!, position)
//                        //return true -> 点击事件已经被消费，不会继续出发点击事件；  else false -> 继续触发点击事件
//                        return true
//                    }
//                })
            }
        }
    }


}