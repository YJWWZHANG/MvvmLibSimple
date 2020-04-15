package com.zqb.mvvm.base

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.noober.background.BackgroundLibrary
import com.zqb.mvvm.base.view.activity.InjectionActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.kodein.di.Kodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

abstract class SimpleActivity : InjectionActivity() {

    private val mBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when(intent.action) {
                "android.intent.action.SCREEN_ON" -> {
                    BarUtils.setNavBarVisibility(this@SimpleActivity, false)
                }
                "android.intent.action.SCREEN_OFF" -> {
                    BarUtils.setNavBarVisibility(this@SimpleActivity, false)
                }
            }
        }
    }

    override val kodein: Kodein by retainedKodein {
        extend(parentKodein)
        bind<Activity>() with instance(this@SimpleActivity)
        importModule(this)
    }

    open fun importModule(mainBuilder: Kodein.MainBuilder) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        BackgroundLibrary.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            getContainer()?.setPadding(
                getContainer()?.paddingLeft ?: 0, (getContainer()?.paddingTop ?: 0) + BarUtils.getStatusBarHeight(),
                getContainer()?.paddingRight ?: 0, getContainer()?.paddingBottom ?: 0
            )
            BarUtils.setNavBarVisibility(this, false)
        }
        ScreenUtils.toggleFullScreen(this)
        EventBus.getDefault().register(this)
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.SCREEN_ON")
        intentFilter.addAction("android.intent.action.SCREEN_OFF")
        registerReceiver(mBroadcastReceiver, intentFilter)
        initDataAndView()
    }

    override fun onResume() {
        super.onResume()
        BarUtils.setNavBarVisibility(this@SimpleActivity, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        unregisterReceiver(mBroadcastReceiver)
    }

    protected open fun onViewCreated() {
    }

    protected open fun initInject() {
    }

    protected abstract val layoutId: Int
    protected abstract fun initDataAndView()

    protected open fun getContainer(): ViewGroup? {
        return null
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageNothingEvent(s: String) {

    }
}