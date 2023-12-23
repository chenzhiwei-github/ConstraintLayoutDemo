package com.lyf.constraintlayoutdemo

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics


/**
 * BaseDialog弹窗基类，
 */
abstract class NewBaseDialog(context: Context) : Dialog(context, R.style.BaseDialog) {
    init {
        initDialogWindow()
    }

    fun initDialogWindow() {
        val window = window ?: return
        //修复背景
        //1、去掉白色的背景
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //修复大小
        //获取需要显示的宽、高
        val metrics = DisplayMetrics()
        //3、当子类决定弹窗宽高
        getWindow()!!.windowManager.defaultDisplay.getMetrics(metrics)
        val frameSize = onSetupDialogFrameSize(metrics.widthPixels, metrics.heightPixels)
        //4、去掉默认padding间距
        window.decorView.setPadding(0, 0, 0, 0)
        val params = window.attributes
        params.width = frameSize[0]
        params.height = frameSize[1]
        window.attributes = params
    }

    /**
     * 子类需要复写该方法，返回需要的宽高
     * xml的跟布局无论设置match_parent、wrap_content、或者固定大小，最终都是由窗口的大小决定，
     * 例如； size[1] = ViewGroup.LayoutParams.WRAP_CONTENT，根布局高度 设置 200dp，是不生效的，
     */
    abstract fun onSetupDialogFrameSize(screenWidth: Int, screenHeight: Int): IntArray


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    abstract fun initView()

}
