package com.jerryyin.kotlindemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jerryyin.kotlindemo.R
import com.jerryyin.kotlindemo.model.ReNews
import kotlinx.android.synthetic.main.activity_news_detail.*

class KDNewsDetailActivity : AppCompatActivity() {

    private var mNews: ReNews? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        supportActionBar!!.hide()

        getIntentData()
        initView()
    }

    private fun getIntentData() {
        val bundle = this.intent?.getBundleExtra(RecyclerViewActivity.KEY_CURRENT_NEWS)
        if (bundle != null) {
            mNews = ReNews(
                    bundle.getInt(RecyclerViewActivity.KEY_NEWS_ID),
                    bundle.getInt(RecyclerViewActivity.KEY_NEWS_IMG),
                    bundle.getString(RecyclerViewActivity.KEY_NEWS_TITLE),
                    bundle.getString(RecyclerViewActivity.KEY_NEWS_TIME)
            )
        }
    }

    private fun initView() {
        //设置 CoordinatorLayout Title标题颜色
//        collapsing_toolbar_layout.setExpandedTitleColor(getResources().getColor(R.color.white))
        collapsing_toolbar_layout.setExpandedTitleTextAppearance(R.style.CollapsingTitle)

        if (mNews == null){
            Toast.makeText(this@KDNewsDetailActivity, "数据初始化失败，请检查！", Toast.LENGTH_SHORT).show()
        }else{
            img_detail_top.setImageDrawable(resources.getDrawable(mNews!!.imgId!!))
            collapsing_toolbar_layout.title = mNews!!.title
        }

    }
}
