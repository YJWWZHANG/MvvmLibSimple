package com.zqb.mvvmlib

import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.uber.autodispose.autoDisposable
import com.zqb.mvvm.base.SimpleFragment
import com.zqb.mvvm.util.RxSchedulers
import com.zqb.mvvmlib.model.ContactViewState
import com.zqb.mvvmlib.model.mainKodeinModule
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class MainFragment(override val layoutId: Int = R.layout.fragment_main) : SimpleFragment() {

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(mainKodeinModule)
    }

    private val mViewModel: MainViewModel by instance()

    override fun initDataAndView() {
        mViewModel.observeViewState()
            .observeOn(RxSchedulers.ui)
            .autoDisposable(scopeProvider)
            .subscribe(this::onNewState)
        mViewModel.getContacts()
    }

    private fun onNewState(state: ContactViewState) {
        if (state.isLoading) {
//            mLoading.showPopupWindow()
        } else {
//            mLoading.dismiss()
        }
        if (state.contactBean != null) {
            LogUtils.e(state.contactBean.toString())
            if (state.contactBean.code == 0) {
            } else {
            }
        }
    }

}