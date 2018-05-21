package com.jerryyin.kotlindemo.base

import android.app.Application
import android.support.annotation.FontRes
import android.support.v7.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * @description Base Application Class
 * @author JerryYin
 * @create 2018-05-21 14:43
 **/
class KDApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}