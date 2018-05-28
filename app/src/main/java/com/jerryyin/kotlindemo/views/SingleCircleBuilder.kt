package com.jerryyin.kotlindemo.views


import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.RectF
import android.support.annotation.FloatRange

import com.zyao89.view.zloading.ZLoadingBuilder

class SingleCircleBuilder : ZLoadingBuilder() {
    //当前动画阶段
    private var mCurrAnimatorState = 0
    private var mStrokePaint: Paint? = null
    private var mOuterCircleRectF: RectF? = null
    //旋转开始角度
    private var mStartRotateAngle: Int = 0
    //旋转角度
    private var mRotateAngle: Int = 0

    override fun initParams(context: Context) {
        //最大尺寸
        val outR = allSize
        //小圆尺寸
        val inR = outR * 0.6f
        //初始化画笔
        initPaint(inR * 0.4f)
        //旋转角度
        mStartRotateAngle = 0
        //圆范围
        mOuterCircleRectF = RectF()
        mOuterCircleRectF!!.set(viewCenterX - outR, viewCenterY - outR, viewCenterX + outR, viewCenterY + outR)
    }

    /**
     * 初始化画笔
     */
    private fun initPaint(lineWidth: Float) {
        mStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mStrokePaint!!.style = Paint.Style.STROKE
        mStrokePaint!!.strokeWidth = lineWidth
        mStrokePaint!!.color = Color.WHITE
        mStrokePaint!!.isDither = true
        mStrokePaint!!.isFilterBitmap = true
        mStrokePaint!!.strokeCap = Paint.Cap.ROUND
        mStrokePaint!!.strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        //外圆
        canvas.drawArc(mOuterCircleRectF!!, (mStartRotateAngle % 360).toFloat(), (mRotateAngle % 360).toFloat(), false, mStrokePaint!!)
        canvas.restore()
    }

    override fun setAlpha(alpha: Int) {
        mStrokePaint!!.alpha = alpha
    }

    override fun prepareStart(animation: ValueAnimator) {

    }

    override fun prepareEnd() {

    }

    override fun computeUpdateValue(animation: ValueAnimator, @FloatRange(from = 0.0, to = 1.0) animatedValue: Float) {
        mStartRotateAngle = (360 * animatedValue).toInt()
        when (mCurrAnimatorState) {
            0 -> mRotateAngle = (OUTER_CIRCLE_ANGLE * animatedValue).toInt()
            1 -> mRotateAngle = OUTER_CIRCLE_ANGLE - (OUTER_CIRCLE_ANGLE * animatedValue).toInt()
            else -> {
            }
        }
    }

    override fun onAnimationRepeat(animation: Animator?) {
        if (++mCurrAnimatorState > FINAL_STATE) {//还原到第一阶段
            mCurrAnimatorState = 0
        }
    }

    override fun setColorFilter(colorFilter: ColorFilter) {
        mStrokePaint!!.colorFilter = colorFilter
    }

    companion object {

        private val OUTER_CIRCLE_ANGLE = 320
        //最终阶段
        private val FINAL_STATE = 2
    }
}
