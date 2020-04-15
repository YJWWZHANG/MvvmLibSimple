package com.zqb.mvvm.base

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.BarUtils
import com.zqb.mvvm.base.view.fragment.InjectionFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.kodein.di.Kodein

abstract class SimpleFragment: InjectionFragment(){

    protected abstract val layoutId: Int

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getContainer()?.setPadding(
                getContainer()?.paddingLeft ?: 0, (getContainer()?.paddingTop ?: 0) + BarUtils.getStatusBarHeight(),
                getContainer()?.paddingRight ?: 0, getContainer()?.paddingBottom ?: 0
            )
        }
        EventBus.getDefault().register(this)
        initDataAndView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    protected open fun getContainer(): ViewGroup? {
        return null
    }

    protected abstract fun initDataAndView()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageNothingEvent(s: String) {

    }

}
