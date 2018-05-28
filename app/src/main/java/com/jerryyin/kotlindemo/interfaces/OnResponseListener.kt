package com.jerryyin.kotlindemo.interfaces

import java.util.*

/**
 * Created by JerryYin on 7/13/16.
 */
interface OnResponseListener<T> {

    fun onSuccess(result: T)

    fun onFailure(errorCode: Int, error: Any)

}