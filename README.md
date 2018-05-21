# KotlinDemo
Kotlin Android Demo for study &amp; test


**1.RecyclerView 展示列表中 ViewHolder的重用机制造成的图片错乱问题：**
    
    解决方法参考：
        https://m.2cto.com/kf/201607/522038.html
    
    
**2.RecyclerView 加入了 ActivityOptionCompat场景动画后，点击跳转时候图片错乱的问题**  

    原因：                      
        创建动画属性参数的时候，绑定的 View 直接给定了布局xml中的 View【img_topic】
        
            var optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@RecyclerViewActivity, img_topic, getString(R.string.trans_img_topic))
        
        这样的话叠加上ViewHolder的复用机制，在RecyclerView复用（item进出屏幕）过之后，效果就变得比较乱；
        
    解决：
        创建动画参数的时候，传入的 View 对象应该是当前点击的 ItemView， 有adapter的 onItemClick（view：Vie, ...）传递过来，再取出其中需要的 childView 即可：
        
            var optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@RecyclerViewActivity, view.img_topic, getString(R.string.trans_img_topic))

**3.Fresco图片加载控件SimpleDraweeView在加载本地图片（res/）时无法加载：**
    
    解决：
        暂时采用这种普通ImageView的设置图片的方式加载。。。
            holder.img.setImageDrawable(c.resources.getDrawable(news.imgId!!))        
        