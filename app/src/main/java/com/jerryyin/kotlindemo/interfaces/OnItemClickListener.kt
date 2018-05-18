package com.jerryyin.kotlindemo.interfaces

import android.view.View

/**
 * @description
 * @author JerryYin
 * @create 2018-05-17 16:40
 **/
interface OnItemClickListener {

    fun onItemClick(view: View, position: Int)

    fun onItemLongClick(view: View, position: Int)

}