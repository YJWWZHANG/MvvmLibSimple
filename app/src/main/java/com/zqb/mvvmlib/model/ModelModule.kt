package com.zqb.mvvmlib.model

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.zqb.mvvmlib.MainViewModel
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

private const val MAIN_MODULE_TAG  = "MAIN_MODULE_TAG"
val mainKodeinModule = Kodein.Module(MAIN_MODULE_TAG) {

    bind<MainViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        ViewModelProviders
            .of((context).activity!!, MainViewModel.MainViewModelFactory.getInstance(instance()))
            .get(MainViewModel::class.java)
    }
}
