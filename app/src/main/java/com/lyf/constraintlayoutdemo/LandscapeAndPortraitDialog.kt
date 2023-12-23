package com.lyf.constraintlayoutdemo

import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import com.blankj.utilcode.util.ScreenUtils

abstract class LandscapeAndPortraitDialog(context: Context) : NewBaseDialog(context) {

    /**
     *横竖屏监听
     */
    private inner class ConfigurationChangedListener : ComponentCallbacks {
        override fun onConfigurationChanged(newConfig: Configuration) {
            onConfigurationChanged()
            applyLandscapeAndPortraitWindowConfig()//兼容横竖屏
        }

        override fun onLowMemory() {

        }
    }

    abstract fun onConfigurationChanged()

    private var mListener: ConfigurationChangedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mListener = ConfigurationChangedListener()
        context.registerComponentCallbacks(mListener)
    }

    override fun onStop() {
        super.onStop()
        context.unregisterComponentCallbacks(mListener)
    }

    override fun onStart() {
        super.onStart()
        //兼容横竖屏
        applyLandscapeAndPortraitWindowConfig()
    }

    private fun applyLandscapeAndPortraitWindowConfig() {
        if (ScreenUtils.isPortrait()) {
            val params = window!!.attributes
            params.gravity = Gravity.BOTTOM
            window!!.attributes = params
            window!!.setWindowAnimations(R.style.dialog_anim_bottom1)
        } else {
            val params = window!!.attributes
            params.gravity = Gravity.CENTER
            window!!.attributes = params
            window!!.setWindowAnimations(R.style.dialog_anim_bottom1)
        }
    }
}