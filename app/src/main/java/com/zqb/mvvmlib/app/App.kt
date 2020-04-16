package com.zqb.mvvmlib.app

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.Utils
import com.zqb.mvvmlib.model.di.netKodeinModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class App: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        bind<Context>() with singleton { this@App }
        import(netKodeinModule)
    }

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}