package com.lyf.constraintlayoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ConstraintLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cl_guideline)
        findViewById<TextView>(R.id.tv1).setOnClickListener {
            ReplaceButtonsDialog(this).show()
        }
    }
}