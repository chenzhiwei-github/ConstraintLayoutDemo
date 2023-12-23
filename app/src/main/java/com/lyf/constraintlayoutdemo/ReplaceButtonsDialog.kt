package com.lyf.constraintlayoutdemo

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.ScreenUtils
import com.drake.brv.utils.grid
import com.drake.brv.utils.setup
import com.lyf.constraintlayoutdemo.databinding.DialogReplaceButtonPopuBinding


class ReplaceButtonsDialog(
    activity: AppCompatActivity,
    private val popupInvoke: ((Int, Int) -> Unit)? = null
) : LandscapeAndPortraitDialog(activity) {

    override fun onSetupDialogFrameSize(screenWidth: Int, screenHeight: Int): IntArray {
        val size = IntArray(2)
        if (ScreenUtils.isPortrait()) {
            size[0] = screenWidth
            size[1] = (ScreenUtils.getScreenHeight() * .75f).toInt()
        } else {
            size[0] = 480.dp
            size[1] = 296.dp
        }
        return size
    }


    override fun onConfigurationChanged() {
        initDialogWindow()
        initView()
    }

    override fun initView() {
        val binding = DataBindingUtil.inflate<DialogReplaceButtonPopuBinding>(
            LayoutInflater.from(context),
            R.layout.dialog_replace_button_popu,
            null,
            false
        )
        setContentView(binding.root)
        binding.cancelIv.setOnClickListener { dismiss() }
        binding.rootFl.setOnClickListener { dismiss() }
        binding.rootCl.setOnClickListener { }//占位
        val spanCount: Int
        if (ScreenUtils.isPortrait()) {
            spanCount = 5
            binding.recyclerView.addItemDecoration(
                UltraSpaceItemDecoration.Builder()
                    .dividerWidth(24.dp, 24.dp)  //mainWidth 主轴   crossWidth 交叉轴
                    .padding(24.dp, 24.dp) // mainWidth 主轴  crossWidth 交叉轴  边缘宽度
                    .build()
            )
        } else {
            spanCount = 6
            binding.recyclerView.addItemDecoration(
                UltraSpaceItemDecoration.Builder()
                    //mainWidth 主轴   crossWidth 交叉轴 ，
                    //垂直主轴 12 ，水平间隔 32 
                    .dividerWidth(12.dp, 32.dp)
                    .padding(48.dp, 48.dp)
                    .build()
            )
        }

        val list: MutableList<String> = ArrayList()
        repeat(30) {
            list.add(it.toString())
        }
        binding.recyclerView.grid(spanCount = spanCount).setup {
            addType<String>(R.layout.adapter_replace_button_item)
        }.models = list


    }
}


