package com.zqb.mvvmlib

import com.zqb.mvvm.base.SimpleActivity

class MainActivity(override val layoutId: Int = R.layout.activity_main) : SimpleActivity() {

    override fun initDataAndView() {
        loadRootFragment(R.id.fl_root, MainFragment())
    }

}
