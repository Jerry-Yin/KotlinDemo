package com.jerryyin.kotlindemo.model

/**
 * @description
 * @author JerryYin
 * @create 2018-05-28 17:38
 **/

class Vkeys(
        val code: Int,
        val cid: Int,
        val userip: String,
        val data: Data) {

    class Data(
            val expiration: Int,
            val items: List<Item>
    )

    class Item(
            val subcode: Int,
            val songmid: String,
            val filename: String,
            val vkey: String
    )
}
